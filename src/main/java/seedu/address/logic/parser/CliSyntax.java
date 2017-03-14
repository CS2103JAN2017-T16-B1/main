package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.logic.parser.ArgumentTokenizer.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("des/");
    public static final Prefix PREFIX_STATUS = new Prefix("sta/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("pr/");
    public static final Prefix PREFIX_ENDTIME = new Prefix("et/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("st/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Patterns definitions */
    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

}
