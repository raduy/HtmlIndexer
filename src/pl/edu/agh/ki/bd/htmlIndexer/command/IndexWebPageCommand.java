package pl.edu.agh.ki.bd.htmlIndexer.command;

import pl.edu.agh.ki.bd.htmlIndexer.Index;

/**
 * Created by raduy on 02.12.14.
 */
public class IndexWebPageCommand implements ICommand {

    private final String cmd;

    public IndexWebPageCommand(String url) {
        this.cmd = url;
    }

    @Override
    public void execute() {

        Index indexer = new Index();
        for (String url : cmd.substring(2).split(" ")) {
            try {
                indexer.indexWebPage(url);
                System.out.println("Indexed: " + url);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error indexing: " + e.getMessage());
            }
        }
    }
}
