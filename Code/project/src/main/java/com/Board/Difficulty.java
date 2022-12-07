package com.Board;

/**
 * Supported game difficulties
 */
public enum Difficulty {
    EASY {
        @Override
        public int getTrapCount() {return 4;}
        @Override
        public int getMinRewardProx() {return 5;}
        @Override
        public int getRewardCount() {return 5;}
        @Override
        public int getEnemyCount() {return 1;}
        @Override
        public int getMinEnemyProx() {return 10;}
        @Override
        public int getBonusRewardCount() {return 3;}
    },
    MEDIUM{
        @Override
        public int getTrapCount() {return 7;}
        @Override
        public int getMinRewardProx() {return 3;}
        @Override
        public int getRewardCount() {return 10;}
        @Override
        public int getEnemyCount() {return 2;}
        @Override
        public int getMinEnemyProx() {return 7;}
        @Override
        public int getBonusRewardCount() {return 2;}
    },
    HARD{},
    INFINITE{};

    public int getTrapCount() {return 11;};
    public int getMinRewardProx() {return 0;};
    public int getRewardCount() {return 15;}
    public int getEnemyCount() {return 3;}
    public int getMinEnemyProx() {return 5;}
    public int getBonusRewardCount() {return 1;}


}
