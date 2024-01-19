package com.src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Finder {

    String FindFirst(String input);
    String ReplaceFirst(String input, String replace);
    String GroupFind(int group, String input);
    boolean exists(String input);

}

class FindBrackets implements Finder {

    Pattern pattern;
    Matcher matcher;

    FindBrackets() {
        pattern = Pattern.compile("[(][^)(]+[)]");
    }

    @Override
    public String FindFirst(String input) {
        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = input.substring(matcher.start(), matcher.end());
        }
        else output = "";

        return output;
    }

    @Override
    public String ReplaceFirst(String input, String replace) {

        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = matcher.replaceFirst(replace);
        }
        else output = input;

        return output;
    }

    @Override
    public boolean exists(String input) {
        matcher = pattern.matcher(input);
        return matcher.find();
    }

    @Override
    public String GroupFind(int group, String input) {
        matcher = pattern.matcher(input);
        if(matcher.find()) return matcher.group(group);
        else return null;
    }

}
class FindCalculateTasks implements Finder {

    Pattern pattern;
    Matcher matcher;

    String num = "-*[0-9]+[.]?[0-9]*";

    FindCalculateTasks(int priority) {
        if(priority >= 2) pattern = Pattern.compile( "(" + num + ")(\\s*[\\^]\\s*)(" + num + ")");
        else if(priority == 1) pattern = Pattern.compile("(" + num + ")(\\s*[/*]\\s*)(" + num + ")");
        else if(priority == 0) pattern = Pattern.compile("(" + num + ")(\\s*[+-]\\s*)(" + num + ")");
        else pattern = Pattern.compile("(" + num + ")(\\s*[/*+^-]\\s*)(" + num + ")");
    }

    @Override
    public String FindFirst(String input) {

        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = input.substring(matcher.start(), matcher.end());
        }
        else output = "";

        return output;

    }

    @Override
    public String ReplaceFirst(String input, String replace) {

        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = matcher.replaceFirst(replace);
        }
        else output = input;

        return output;
    }

    @Override
    public boolean exists(String input) {
        matcher = pattern.matcher(input);
        return matcher.find();
    }

    @Override
    public String GroupFind(int group, String input) {
        matcher = pattern.matcher(input);
        if(matcher.find()) return matcher.group(group);
        else return null;
    }
}
class FindSymbols implements Finder {

    Pattern pattern;
    Matcher matcher;

    String charToFind;

    FindSymbols(String input) {
        charToFind = input;
        pattern = Pattern.compile(String.valueOf(charToFind));
    }

    @Override
    public String FindFirst(String input) {
        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = input.substring(0, matcher.end());
        }
        else output = "";

        return output;
    }

    @Override
    public String ReplaceFirst(String input, String replace) {

        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = matcher.replaceFirst(replace);
        }
        else output = input;

        return output;
    }

    @Override
    public boolean exists(String input) {
        matcher = pattern.matcher(input);
        return matcher.find();
    }

    @Override
    public String GroupFind(int group, String input) {
        matcher = pattern.matcher(input);
        if(matcher.find()) return matcher.group(group);
        else return null;
    }

}
class FindHeaderOfXmlFiles implements Finder{

    Pattern pattern;
    Matcher matcher;

    FindHeaderOfXmlFiles() {
        pattern = Pattern.compile("<\\?xml.+\\?>");
    }

    @Override
    public String FindFirst(String input) {
        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = input.substring(matcher.start(), matcher.end());
        }
        else output = "";

        return output;
    }

    @Override
    public String ReplaceFirst(String input, String replace) {

        String output;

        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            output = matcher.replaceFirst(replace);
        }
        else output = input;

        return output;
    }

    @Override
    public boolean exists(String input) {
        matcher = pattern.matcher(input);
        return matcher.find();
    }

    @Override
    public String GroupFind(int group, String input) {
        matcher = pattern.matcher(input);
        if(matcher.find()) return matcher.group(group);
        else return null;
    }

}


