package com.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * All support objects that BoardData may contain
 */
public enum Objects {
    HERO,
    ENEMY,
    REWARD {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 15, row * tileHeight+60 + 15, tileWidth/2, tileHeight/2, null);
        }
         @Override
        public String toString() {
            return "reward";
        }
    },
    BONUS {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth, row * tileHeight + 60, tileWidth/2, tileHeight/2, null);
        }
         @Override
        public String toString() {
            return "bonus";
        }
    },
    TRAP {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth+20, row * tileHeight+60+20, (int)(tileWidth*(1.0/3)), (int)(tileHeight*(1.0/3)), null);
        }
         @Override
        public String toString() {
            return "trap";
        }
    },
    TREE,
    EXIT {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth, row * tileHeight+60, tileWidth, tileHeight, null);        }
         @Override
        public String toString() {
            return "exit";
        }
    },
    EMPTY {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 10, row * tileHeight + 60 + 10, (int) (tileWidth * (2.0 / 3)), (int) (tileHeight * (2.0 / 3)), null);
        }
         @Override
        public String toString() {
            return "empty";
        }
    },
    TMP,
    ENEMYANDREWARD {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 15, row * tileHeight+60 + 15, tileWidth/2, tileHeight/2, null);
        }
         @Override
        public String toString() {
            return "reward";
        }
    },
    ENEMYANDTRAP {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth+20, row * tileHeight+60+20, (int)(tileWidth*(1.0/3)), (int)(tileHeight*(1.0/3)), null);
        }
         @Override
        public String toString() {
            return "trap";
        }
    },
    ENEMYANDBUSH {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 10, row * tileHeight+60 + 10, (int)(tileWidth*(2.0/3)), (int)(tileHeight*(2.0/3)), null);
        }
         @Override
        public String toString() {
            return "bush";
        }
    },
    HEROHIDDEN {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 10, row * tileHeight + 60 + 10, (int) (tileWidth * (2.0 / 3)), (int) (tileHeight * (2.0 / 3)), null);
        }
         @Override
        public String toString() {
            return "bush";
        }
    }, BUSH {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 10, row * tileHeight+60 + 10, (int)(tileWidth*(2.0/3)), (int)(tileHeight*(2.0/3)), null);
        }
         @Override
        public String toString() {
            return "bush";
        }
    },
    ENEMYANDBONUS {
        @Override
        public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
            g2.drawImage(png, col * tileWidth + 10, row * tileHeight + 60 + 10, (int) (tileWidth * (2.0 / 3)), (int) (tileHeight * (2.0 / 3)), null);
        }
         @Override
        public String toString() {
            return "bonus";
        }
    };

    public void draw(Graphics2D g2, int col, int row, BufferedImage png, int tileHeight, int tileWidth) {
    }

    ;
    }

