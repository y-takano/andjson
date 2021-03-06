package jp.gr.java_conf.ke.json;

public class Token {

	private Symbol symbol;
	private volatile char[] name;
	private Value value;

	Token() {
		this.reset(Symbol.UNDEFINED, null, Value.OF_NULL);
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public boolean isName() {
		return name != null;
	}

	public boolean isValue() {
		return !isName() && !isSymbol();
	}

	public boolean isSymbol() {
		return !symbol.equals(Symbol.UNDEFINED);
	}

	public boolean isThat(Symbol symbol) {
		return this.symbol.equals(symbol);
	}

	public String getName() {
		return name != null ? String.valueOf(name) : null;
	}

	public Value getValue() {
		return value;
	}

	public String toString() {
		return symbol != Symbol.UNDEFINED ? "SYMBOL:" + symbol
				: isValue() ? value.toString() : "NAME  :\"" + new String(name) + "\"";
	}

	public static Token createSymbol(Symbol symbol) {
		Token token = TokenRecycler.getToken();
		token.reset(symbol, null, Value.OF_NULL);
		return token;
	}

	public static Token createNullValue() {
		Token token = TokenRecycler.getToken();
		token.reset(Symbol.UNDEFINED, null, Value.OF_NULL);
		return token;
	}

	public static Token createValue(ValueType type, CharSequence text) {
		Value value;
		if (type == null || text == null) {
			value = Value.OF_NULL;

		} else {

			char t[] = new char[text.length()];
			for (int i=0; i<t.length; i++) {
				t[i] = text.charAt(i);
			}
			value = new Value(type, t);
		}

		Token token = TokenRecycler.getToken();
		token.reset(Symbol.UNDEFINED, null, value);
		return token;
	}

	public static Token createName(CharSequence name) {
		char n[] = new char[name.length()];
		for (int i=0; i<n.length; i++) {
			n[i] = name.charAt(i);
		}
		Token token = TokenRecycler.getToken();
		token.reset(Symbol.UNDEFINED, n, Value.OF_NULL);
		return token;
	}

	public static void releaseToken(Token token) {
		TokenRecycler.offer(token);
	}

	private void reset(Symbol symbol, char[] name, Value value) {
		this.symbol = symbol;
		this.name = name;
		this.value = value;
	}
}
