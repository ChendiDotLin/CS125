//UIUC CS125 FALL 2013 MP. File: Person.java, CS125 Project: Challenge6-RecursionSee, Version: 2013-11-05T16:10:13-0600.047294320
/**
 * @author bfu3
 *
 */
public class Person
{
private final String name;
private final int age;
private final char gender;
private final Person child1; //left child
private final Person child2; //right child

public Person(String name, int age, char gender, Person c1, Person c2)
{
    this.name=name;
    this.age=age;
    this.gender=gender;
    this.child1 = c1;
    this.child2 = c2;
}

public String toString()
{
    return name+"*"+age+"*"+gender;
}

public String getName() 
{
	return name;
}

public int getAge() 
{
	return age;
}

public char getGender() 
{
	return gender;
}

public boolean equals(Person p)
{
	return (this.name.equals(p.name)) &&
			(this.age==p.age) &&
			(this.gender==p.gender);
}


public void print() 
{
   System.out.println(this);
   if(child1 != null)
       child1.print();
   if(child2 != null)
       child2.print();
   
}

public int count() // total person count including this object
{
	if (this.child1!=null && this.child2!=null) {
		return 1 + this.child1.count() + this.child2.count();
	}
	else if (this.child1!=null && this.child2==null) {
		return 1 + this.child1.count();
	}
	else if (this.child1==null && this.child2!=null) {
		return 1 + this.child2.count();
	}
	else
		return 1;
}

public int countGrandChildren() // but not greatGrandChildren
{
	if (this.child1!=null && this.child2!=null) {
		return this.child1.countChildren() + this.child2.countChildren();
	}
	else if (this.child1!=null && this.child2==null) {
		return this.child1.countChildren();
	}
	else if (this.child1==null && this.child2!=null) {
		return this.child2.countChildren();
	}
	else
		return 0;
}

public int countChildren()
{
	if (this.child1!=null && this.child2!=null) {
		return 2;
	}
	else if (this.child1!=null && this.child2==null) {
		return 1;
	}
	else if (this.child1==null && this.child2!=null) {
		return 1;
	}
	else
		return 0;
}	

public int countMaxGenerations()
{
	if (this.child1!=null && this.child2!=null) {
		return 1 + Math.max(this.child1.countMaxGenerations(), this.child2.countMaxGenerations());
	}
	else if (this.child1!=null && this.child2==null) {
		return 1 + this.child1.countMaxGenerations();
	}
	else if (this.child1==null && this.child2!=null) {
		return 1 + this.child2.countMaxGenerations();
	}
	else
		return 1;
}

public int countGender(char gen)
{
	int result = (this.gender==gen ? 1:0);

	if (this.child1!=null && this.child2!=null) {
		return result + this.child1.countGender(gen) + this.child2.countGender(gen);
	}
	else if (this.child1!=null && this.child2==null) {
		return result + this.child1.countGender(gen);
	}
	else if (this.child1==null && this.child2!=null) {
		return result + this.child2.countGender(gen);
	}
	else
		return result;
}


public Person search(String name, int maxGeneration)
{
	if (maxGeneration>0) {
		if (this.name.equals(name)) return this;
		if (this.child1==null && this.child2==null) return null;

		if (this.child1!=null && this.child2!=null) {
			if (this.child1.search(name, maxGeneration-1) != null)
				return this.child1.search(name, maxGeneration-1);
			else
				return this.child2.search(name, maxGeneration-1);
		}
		else if (this.child1!=null && this.child2==null)
			return this.child1.search(name, maxGeneration-1);
		else if (this.child1==null && this.child2!=null)
			return this.child2.search(name, maxGeneration-1);
	}
	else return null;
	
	return null;
}

} // end of class
