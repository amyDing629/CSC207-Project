import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Presenter class of Meeting System
 * Iterates through a list of String prompts
 * ~~~A modified copy of lecture code --- StudentPropertiesIterator ~~~
 */
public class MeetingIterator implements Iterator<String> {
    private List<String> properties = new ArrayList<>();
    private int current = 0;

    /**
     * Hardcodes the prompts into a List of Strings
     */
    public MeetingIterator() {
        properties.add("Date-time: (should be in pattern of \"yyyy-MM-dd HH:mm\")");
        properties.add("Place: ");
        properties.add("Other User ID: ");
    }

    /**
     * Checks for subsequent prompts.
     * @return true if there is prompt that has not yet been returned.
     */
    @Override
    public boolean hasNext() {
        return current < properties.size();
    }

    /**
     * Returns the next prompt to be printed.
     * @return the next prompt.
     */
    @Override
    public String next() {
        String res;

        // List.get(i) throws an IndexOutBoundsException if
        // we call it with i >= properties.size().
        // But Iterator's next() needs to throw a
        // NoSuchElementException if there are no more elements.
        try {
            res = properties.get(current);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        current += 1;
        return res;
    }

    /**
     * Removes the prompt just returned. Unsupported.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }

}
