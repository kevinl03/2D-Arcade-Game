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
    }
}
