#include <string>
#include <cassert>
#include <memory>
#include <stdexcept>
#include <vector>
#include <iostream>
#include <cstdio>
#include <stdlib.h>

using namespace std;

struct Token {
	enum Type {value, operation, opening_bracket, closing_bracket};
	Type type;
	string text;
};

struct Tokenizer {
public:
	Tokenizer() {
        i = 0;
		content = generate();
		pos = content.begin();
	};
	const Token* peek() {
		return pos!=content.end() ? &*pos : 0 ;
	};
	const Token* get() {
		if(pos!=content.end()) {
			cout << pos->text << " ";
		}
		return pos!=content.end() ? &*pos++ : 0 ;
	};
private:
	vector<Token> generate(int level=0);
private:
	vector<Token>::iterator pos;
	vector<Token> content;
    int i;
};

class Expression;
typedef struct auto_ptr<Expression> ExpressionPtr;

class Expression {
public:
	Expression() {}
	virtual ~Expression() {}
	static ExpressionPtr parse(Tokenizer& tokens);
	virtual float calc()=0;
	virtual void debug(string prefix)=0;
};

class Value: public Expression {
public:
	Value() {}
	virtual ~Value() {}
	static bool isItYou(Tokenizer& tokens);
	static ExpressionPtr parse(Tokenizer& tokens);
	virtual float calc() {
		return _value;
	}
	virtual void debug(string prefix) {
		cout << prefix << "Value(" << calc() << "):" << _value << endl;
	}
protected:
	float _value;
};

class Operation: public Expression {
public:
	Operation() {}
	virtual ~Operation() {}
	static ExpressionPtr parse(Tokenizer& tokens, ExpressionPtr& l);
	virtual float calc();
	virtual void debug(string prefix) {
		cout << prefix << "Operation(" << calc() << "): " << _operation << endl;
		if(_left.get()) {
			_left->debug(prefix+" ");
		}
		if(_right.get()) {
			_right->debug(prefix+" ");
		}
	}
protected:
	std::auto_ptr<Expression> _left;
	std::auto_ptr<Expression> _right;
	string _operation;
};

class PriorityOperation: public Operation {
public:
	PriorityOperation() {}
	virtual ~PriorityOperation() {}
	static ExpressionPtr parse(Tokenizer& tokens, ExpressionPtr& left);
	virtual void debug(string prefix) {
		cout << prefix << "PriorityOperation(" << calc() << "): " << _operation << endl;
		if(_left.get()) {
			_left->debug(prefix+" ");
		}
		if(_right.get()) {
			_right->debug(prefix+" ");
		}
	}
};

class BracketExpression: public Expression {
public:
	static bool isItYou(Tokenizer& tokens);
	static ExpressionPtr parse(Tokenizer& tokens);
	virtual float calc() {
		return _internal->calc();
	}
	virtual void debug(string prefix) {
		cout << prefix << "Brackets(" << calc() << "): " << endl;
		_internal->debug(prefix+" ");
	}
protected:
	std::auto_ptr<Expression> _internal;
};

ExpressionPtr Expression::parse(Tokenizer& tokens) {
	if(!tokens.peek()) {
		return ExpressionPtr();
	}
	if(BracketExpression::isItYou(tokens)) {
		return BracketExpression::parse(tokens);
	} else if(Value::isItYou(tokens)) {
		return Value::parse(tokens);
	} else {
		throw logic_error("(Expression) Wtf is that: " + tokens.peek()->text);
	}
}

bool Value::isItYou(Tokenizer& tokens) {
	const Token* t = tokens.peek();
	if(!t||t->type!=Token::value) return false;
	char* endptr;
	strtod(t->text.c_str(), &endptr);
	return *endptr==0;
}

ExpressionPtr Value::parse(Tokenizer& tokens) {
	std::auto_ptr<Value> foo(new Value);
	const Token* t = tokens.get();
	assert(t&&t->type==Token::value);
	char* endptr;
	foo->_value = strtod(t->text.c_str(), &endptr);
	return ExpressionPtr(foo.release());
}

bool BracketExpression::isItYou(Tokenizer& tokens) {
	return tokens.peek()&&tokens.peek()->type==Token::opening_bracket;
}

ExpressionPtr BracketExpression::parse(Tokenizer& tokens) {
	const Token* t = tokens.get();
	assert(t->type == Token::opening_bracket);
	auto_ptr<BracketExpression> result(new BracketExpression);
	ExpressionPtr null;
	result->_internal = Operation::parse(tokens, null);
	t = tokens.get();
	if(t==0||t->type!=Token::closing_bracket) {
		throw logic_error("(BracketExpression) mismatched brackets ");
	}
	return ExpressionPtr(result.release());
}

ExpressionPtr Operation::parse(Tokenizer& tokens, ExpressionPtr& l) {
	ExpressionPtr left;
	if(l.get()) {
		left = l;
	} else {
		left = Expression::parse(tokens);
	}
	const Token* t = tokens.peek();
	if(!t||t->type==Token::closing_bracket) {
		return left;
	}
	if(t->type==Token::operation&&(t->text=="*"||t->text=="/")) {
		ExpressionPtr result = PriorityOperation::parse(tokens, left);
		left = result;
		t = tokens.peek();
		if(!t||t->type==Token::closing_bracket) {
			return left;
		}
	}
	if(t->type==Token::operation&&(t->text=="+"||t->text=="-")) {
		tokens.get();
		auto_ptr<Operation> result(new Operation);
		result->_operation = t->text;
		result->_left=left;
		ExpressionPtr foo = Expression::parse(tokens);
		const Token* t = tokens.peek();
		if(t!=0&&(t->text=="*"||t->text=="/")) {
			foo = PriorityOperation::parse(tokens, foo);
		}
		result->_right = foo;
		ExpressionPtr bar(result.release());
		return Operation::parse(tokens, bar);
	} else {
		throw logic_error("(Operation) Wtf is that: " + tokens.peek()->text);
	}
}

ExpressionPtr PriorityOperation::parse(Tokenizer& tokens, ExpressionPtr& left)
{
    const Token *t=tokens.peek();
    if (!t || t->type == Token::closing_bracket  ) return left; //just return Value, sorry no operation guys

        if (t->type == Token::operation && (t->text=="*" || t->text=="/") )
        {
                tokens.get(); //commit previuos peek

                auto_ptr<PriorityOperation> result ( new PriorityOperation ); 
                result->_operation = t->text;
                result->_left=left;
                result->_right=Expression::parse(tokens);
                ExpressionPtr rs(result.release());

                return PriorityOperation::parse(tokens, rs);

        }
        else if (t->type == Token::operation && (t->text=="+" || t->text=="-") )
        {
                return left;
        }
        else
        {
                throw logic_error("(PriorityOperation) Wtf is that: " + tokens.peek()->text );
        }
}

float Operation::calc()
{
        if (_operation == "+")
        {
                float l=_left.get()?_left->calc():0.0f;
                float r=_right.get()?_right->calc():0.0f;
                return l+r;
        }
        else
        if (_operation == "-")
        {
                float l=_left.get()?_left->calc():0.0f;
                float r=_right.get()?_right->calc():0.0f;
                return l-r;
        }        
        else
        if (_operation == "*")
        {
                float l=_left.get()?_left->calc():1.0f;
                float r=_right.get()?_right->calc():1.0f;
                return  l*r; 
        }        
        else
        if (_operation == "/")
        {
                float l = _left.get()?_left->calc():1.0f;
                float r = _right.get()?_right->calc():1.0f;
                return l/r;
        }
        else
        {
                throw logic_error("Wft: operation udefined");
        }                
}

int main() {
	try {
        Tokenizer tk;
        ExpressionPtr null;
        ExpressionPtr foo = Operation::parse(tk, null);
        cout<<endl;
        foo->debug("");
        float result = foo->calc();
        cout<<"result = "<<result<<endl;
    } catch(exception& e) {
        cout<<e.what()<<endl;
        return 1;
    }
    return 0;
}

class Class {
	int main() {
	}
};

vector<Token> Tokenizer::generate(int level) {
	if(level>3) {
		Token t;
		char f[100];
		snprintf(f, 100, "%d", int(rand()%100));
		t.text = f;
		t.type=Token::value;
		return vector<Token>(&t,&t+1);
	}
	if(rand()%10==0) {
		vector<Token> result;
		Token t1,t2;
		t1.type=Token::opening_bracket;
		t1.text="(";
        i++;
        printf("%d\n",i);
		t2.type = Token::closing_bracket;
		t2.text=")";
		result.push_back(t1);
		vector<Token> r=generate(level+1);
		result.insert(result.end(),r.begin(),r.end());
		result.push_back(t2);
		return result;
	}
	char op="+-*/"[rand()%4];
    i++;
    printf("%d %c\n",i,op);
	Token t;
	t.type=Token::operation;
	t.text=op;
	vector<Token> result=generate(level+1);
	result.push_back(t);
	vector<Token> r2=generate(level+1);
	result.insert(result.end(),r2.begin(),r2.end());
	return result;
}
