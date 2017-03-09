package seedu.address.model.assignment;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;


public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent Event as the given argument.
     */
    public boolean contains(ReadOnlyEvent toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a Event to the list.
     *
     * @throws DuplicateEventException if the Event to add is a duplicate of an existing Event in the list.
     */
    public void add(Event toAdd) throws DuplicateEventException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
    }

    /**
     * Updates the Event in the list at position {@code index} with {@code editedEvent}.
     *
     * @throws DuplicateEventException if updating the Event's details causes the Event to be equivalent to
     *      another existing Event in the list.
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void updateEvent(int index, ReadOnlyEvent editedEvent) throws DuplicateEventException {
        assert editedEvent != null;

        Event EventToUpdate = internalList.get(index);
        if (!EventToUpdate.equals(editedEvent) && internalList.contains(editedEvent)) {
            throw new DuplicateEventException();
        }

        EventToUpdate.resetData(editedEvent);
        // TODO: The code below is just a workaround to notify observers of the updated Event.
        // The right way is to implement observable properties in the Event class.
        // Then, EventCard should then bind its text labels to those observable properties.
        internalList.set(index, EventToUpdate);
    }

    /**
     * Removes the equivalent Event from the list.
     *
     * @throws EventNotFoundException if no such Event could be found in the list.
     */
    public boolean remove(ReadOnlyEvent toRemove) throws EventNotFoundException {
        assert toRemove != null;
        final boolean EventFoundAndDeleted = internalList.remove(toRemove);
        if (!EventFoundAndDeleted) {
            throw new EventNotFoundException();
        }
        return EventFoundAndDeleted;
    }

    public void setEvents(UniqueEventList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setEvents(List<? extends ReadOnlyEvent> Events) throws DuplicateEventException {
        final UniqueEventList replacement = new UniqueEventList();
        for (final ReadOnlyEvent Event : Events) {
            replacement.add(new Event(Event));
        }
        setEvents(replacement);
    }

    public UnmodifiableObservableList<Event> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEventList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueEventList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateEventException extends DuplicateDataException {
        protected DuplicateEventException() {
            super("Operation would result in duplicate Events");
        }
    }

    /**
     * Signals that an operation targeting a specified Event in the list would fail because
     * there is no such matching Event in the list.
     */
    public static class EventNotFoundException extends Exception {}

}
