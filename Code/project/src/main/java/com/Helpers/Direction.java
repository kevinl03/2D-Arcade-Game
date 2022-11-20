package com.Helpers;

/**
 * Contains all support directions, along with null and random
 * null is used to stay stationary
 * random is used when requiring a random direction
 *
 * contains a twoString method for each enum that returns the direction in UpperCamelCase as a string
 */
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
    }, NULL, RANDOM
}
