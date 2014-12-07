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
        String[] urls = cmd.substring(2).split(" ");

        if (urls.length == 0) {
            urls = new String[]{"http://google.com"};
        }

        for (String url : urls) {
            try {
                indexer.indexWebPage(url.trim());
                System.out.println("Indexed: " + url);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error indexing: " + e.getMessage());
            }
        }
    }
}
