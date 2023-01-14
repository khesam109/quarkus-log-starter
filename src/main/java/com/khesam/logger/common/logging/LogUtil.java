package com.khesam.logger.common.logging;


import java.io.*;
import java.util.Arrays;

public class LogUtil {

    private static final String RELEVANT_PACKAGE[] = new String[]{"com.example."};
    private static final String[] BLACK_LIST_WORDS = new String[]{"_ClientProxy", "_Subclass","io.quarkus.arc.impl."};
    private static final String STANDARD_STAKTRACE_PREFIX = "at ";
    private static final String SKIPPING_LINES_STRING = "\t...";
    private static final String CAUSE_STAKTRACE_PREFIX = "Caused by:";
    private static final String SUPPRESED_STAKTRACE_PREFIX = "Suppressed:";

    public static String getStacktrace(Throwable e, String[] relevantPackage) {
        ByteArrayOutputStream stacktraceContent = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(stacktraceContent));

        return extractStackTrace(relevantPackage, BLACK_LIST_WORDS, stacktraceContent);
    }

    private static String extractStackTrace(String[] relevantPackage, String[] blackListWords, ByteArrayOutputStream stacktraceContent) {
        StringBuilder result = new StringBuilder("\n");

        String[] relPacks = (relevantPackage != null && relevantPackage.length != 0) ? relevantPackage : RELEVANT_PACKAGE;
        if (stacktraceContent.size() > 0) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(stacktraceContent.toByteArray())))) {
                String line = reader.readLine();
                do {
                    line = traverseSingularStacktrace(result, relPacks, blackListWords, reader, line);
                } while (line != null);
            } catch (IOException ioe) {
                result.delete(0, result.length()).append(stacktraceContent);
            }
        }
        return result.toString();
    }


    private static String traverseSingularStacktrace(StringBuilder result, String[] relPacks, String[] blackListWords, BufferedReader reader, String line) throws IOException {

        result.append(line).append("\n");
        boolean toBePrinted = true;
        boolean relevantPackageReached = false;
        boolean isCurLineRelevantPack = false;
        boolean blackWordContains = false;
        boolean skipLineToBePrinted = false;

        while ((line = reader.readLine()) != null) {
            String trimmedLine = line.trim();
            if (trimmedLine.startsWith(STANDARD_STAKTRACE_PREFIX)) {
                String stack = trimmedLine.substring(STANDARD_STAKTRACE_PREFIX.length());

                blackWordContains = Arrays.stream(blackListWords).anyMatch(stack::contains);
                isCurLineRelevantPack = Arrays.stream(relPacks).anyMatch(stack::startsWith);

                if (!blackWordContains) {
                    if (!relevantPackageReached && isCurLineRelevantPack) {
                        relevantPackageReached = true;
                        skipLineToBePrinted = false;
                        toBePrinted=true;
                    }
                } else {
                    toBePrinted = false;
                }

                if (toBePrinted) {
                    result.append(line).append("\n");
                } else if (skipLineToBePrinted) {
                    result.append(SKIPPING_LINES_STRING).append("\n");
                    skipLineToBePrinted = false;
                }

                if (relevantPackageReached && !isCurLineRelevantPack) {
                    relevantPackageReached = false;
                    toBePrinted = false;
                    skipLineToBePrinted = true;
                }
            } else {
                /*
                 * This "else" branch deals with lines in the stacktrace that either start next singular stacktrace or are the last line in current
                 * singular stacktrace and it is of the form "... X more" where X is a number.
                 */
                if (trimmedLine.startsWith(CAUSE_STAKTRACE_PREFIX) || trimmedLine.startsWith(SUPPRESED_STAKTRACE_PREFIX)) {
                    /*
                     * If this is the first line of next singular stacktrace we break out of the current cycle and return this line for the next
                     * iteration which will invoke this method again
                     */
                    break;
                    /*
                     * If it is last line in current singular stacktrace that starts with "..." then we print it if needed based on the value of the
                     * flag "toBePrinted" or in the case if we needed to add our line "..." instead we just print the original line as it has also the
                     * number of skipped lines
                     */
                } else if (toBePrinted || skipLineToBePrinted) {
                    result.append(line).append("\n");
                    skipLineToBePrinted = false;
                }
            }
        }
        return line;
    }

}
