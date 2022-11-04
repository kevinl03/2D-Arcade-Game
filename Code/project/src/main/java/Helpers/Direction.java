package Helpers;

public enum Direction {
    NORTH{
        @Override
        public String toString() {
            return "North";
        }
    }, EAST{
        @Override
        public String toString() {
            return "East";
        }
    }, SOUTH{
        @Override
        public String toString() {
            return "South";
        }
    }, WEST{
        @Override
        public String toString() {
            return "West";
        }
    }, NULL
}
