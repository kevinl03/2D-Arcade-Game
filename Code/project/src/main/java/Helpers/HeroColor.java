package Helpers;

public enum HeroColor {


    BROWN{
        public String toString() {
            return "Brown";
        }},

    RED{
            public String toString(){
                return "Red";
            }
        },
    GREY{
        @Override
        public String toString() {
            return "Grey";
        }
    },
    WHITE{
        @Override
        public String toString() {
            return "White";
        }
    };

    private static HeroColor[] colors = values();

    public HeroColor next(){
        return colors[(this.ordinal() + 1) % colors.length];
    }
    public HeroColor prev(){
        return colors[(ordinal() - 1  + colors.length) % colors.length];
    }

}
