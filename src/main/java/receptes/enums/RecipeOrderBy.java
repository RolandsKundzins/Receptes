package receptes.enums;

public enum RecipeOrderBy {
    NOSAUKUMSASC("nosaukums augoši", "r.nosaukums ASC"),
    NOSAUKUMSDESC("nosaukums dilstoši", "r.nosaukums DESC"),
    
    PAGATAVOSANASLAIKSASC("ilgums dilstoši", "r.pagatavosanasLaiks ASC"),
    PAGATAVOSANASLAIKSDESC("ilgums augoši", "r.pagatavosanasLaiks DESC"),
    
    PIEVIENOSANASDATUMSASC("pievienots augoši", "r.pievienosanasDatums ASC"),
    PIEVIENOSANASDATUMSDESC("pievienots dilstoši", "r.pievienosanasDatums DESC"),
    
    KATEGORIJAASC("pievienots augoši", "ek.nosaukums ASC"),
    KATEGORIJADESC("pievienots dilstoši", "ek.nosaukums DESC"),
    
    SKATSKAITSASC("skatījumi augoši", "wc.skatSkaits ASC"),
	SKATSKAITSDESC("skatījumi dilstoši", "wc.skatSkaits DESC"),
	
    LIETOTAJVARDSASC("autors augoši", "l.lietotajvards ASC"),
    LIETOTAJVARDSDESC("autors dilstoši", "l.lietotajvards DESC");

    private final String sqlLauks;
    private final String paradamaisNosaukums;

    RecipeOrderBy(String paradamaisNosaukums, String sqlLauks) {
        this.sqlLauks = sqlLauks;
        this.paradamaisNosaukums = paradamaisNosaukums;
    }
    
    public String getSqlLauks() {
    	return sqlLauks;
    }
    
    public String getParadamaisNosaukums() {
    	return paradamaisNosaukums;
    }
}
