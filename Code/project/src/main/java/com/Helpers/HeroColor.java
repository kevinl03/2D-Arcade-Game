package com.Helpers;

/**
 * all supported hero colors
 *
 * contains a twoString method for each enum that returns the color in UpperCamelCase as a string
 */
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

    /**
     * array of all types of HeroColor
     */
    private static HeroColor[] colors = values();

    /**
     * When called on a HeroColor, the next color in colors array is returned,
     * if on the last color, first color is returned
     * @return Next Color in enum (loops)
     */
    public HeroColor next(){
        return colors[(this.ordinal() + 1) % colors.length];
    }

    /**
     * When called on a HeroColor, the previous color in colors array is returned,
     * if on the first color, last color is returned
     * @return Previous Color in enum (loops)
     */
    public HeroColor prev(){
        return colors[(ordinal() - 1  + colors.length) % colors.length];
    }

    public static HeroColor[] getColors() {
        return colors;
    }
}
