package seedu.address.model.assignment;

import java.util.Objects;

import seedu.address.model.tag.UniqueTagList;

public class Assignment {

	protected Name Name;
	protected Description description;
	protected UniqueTagList tags;

	public Assignment() {
		super();
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

	public UniqueTagList getTags() {
	    return new UniqueTagList(tags);
	}

	/**
	* Replaces this task's tags with the tags in the argument tag list.
	*/
	public void setTags(UniqueTagList replacement) {
	    tags.setTags(replacement);
	}

	

}