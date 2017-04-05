package ua.com.juja.sqlweb.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SettingsHelper {

    private void toSettings(String[] data, ArrayList<String[]> settings, int index) {
        String[] tmp = new String[2];
        tmp[0] = data[index];
        tmp[1] = data[index + 1];
        settings.add(tmp);
    }

    public ArrayList<String[]> addSettings(String[] data, ArrayList<String[]> settings) {
        int index = 0;
        String temp = "";
        if(settings.size() == 1){
            temp += settings.get(0)[0];
        }

        while (index != data.length) {
            if(!temp.equals("")){
                if(temp.equals(data[index])){
                    index += 2;
                    continue;
                }
            }
            toSettings(data, settings, index);
            index += 2;
        }
        return settings;
    }
 }
