package com.example.demo.algorithm;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChineseRing {
    private ArrayList<ArrayList<Boolean>> matrix;

    public String serialise() {
        String toReturn = "[";
        
        for(int y = 0; y < matrix.size(); y++) {
            ArrayList<Boolean> currentLine = matrix.get(y);
            toReturn += "[";

            for(int i = 0; i < currentLine.size(); i++) {
                toReturn += currentLine.get(i).toString();

                if(i == currentLine.size() - 1) {
                    break;
                }

                toReturn += ",";
            }
            toReturn += "]";

            if(y == matrix.size() - 1) {
                break;
            }

            toReturn += ",";
        }

        return toReturn;
    }
}