package seedu.address.model.assignment;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;

public class Task extends Assignment implements ReadOnlyTask {
	private DeadLine deadLine;
	
	public Task(Name name, Description description, DeadLine deadLine, UniqueTagList tags){
		assert !CollectionUtil.isAnyNull(name, deadLine, description, tags);
		this.Name=name;
		this.description=description;
		this.deadLine=deadLine;
		this.tags=tags;
	}
	
	public Task(ReadOnlyTask source) {
		this(source.getName(), source.getDescription(), source.getDeadLine(), source.getTags());
	}

	public void setName(Name name) {
	    assert name != null;
	    this.Name = name;
	}

	public Name getName() {
	    return this.Name;
	}
	
	public void setDescription(Description description) {
	    assert description != null;
	    this.description = description;
	}

	public Description getDescription() {
	    return this.description;
	}
	
	public void setDeadLine(DeadLine deadLine) {
	    assert deadLine != null;
	    this.deadLine = deadLine;
	}

	public DeadLine getDeadLine() {
	    return deadLine;
	}
	
	public UniqueTagList getTags() {
	    return new UniqueTagList(tags);
	}

	/**
	* Replaces this task's tags with the tags in the argument tag list.
	*/
	public void setTags(UniqueTagList replacement) {
	    tags.setTags(replacement);
	}
	
	/**
     * Updates this task's with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setDescription(replacement.getDescription());
        this.setDeadLine(replacement.getDeadLine());
        this.setTags(replacement.getTags());
    }
    
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }
    
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(Name, description, deadLine, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }
}
