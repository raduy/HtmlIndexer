package pl.edu.agh.ki.bd.htmlindexer.command.impl;

import pl.edu.agh.ki.bd.htmlindexer.command.api.ICommand;

/**
 * Created by raduy on 07.12.14.
 */
public class PrintHelpCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("'?'      	- print this help");
        System.out.println("'x'      	- exit HtmlIndexer");
        System.out.println("'i URLs'  	- index URLs, space separated");
        System.out.println("'f WORDS'	- find sentences containing all WORDs, space separated");
        System.out.println("'l <limit>  - find sentences with size at least limit'");
        System.out.println("'c          - count word occurrences in word index'");
        System.out.println("'t          - draw indexed urls table'");
        System.out.println("'q          - list all indexed sentences and url in which they were found'");
    }
}
