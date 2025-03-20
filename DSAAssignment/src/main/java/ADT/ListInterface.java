/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ADT;
//@author kahfui
public interface ListInterface<T extends Comparable<T>> {


  public boolean add(T newEntry);

  public boolean remove(T anEntry);

  public boolean contains(T anEntry);

  public void clear();

  public int getNumberOfEntries();

  public boolean isEmpty();
  
  public T get(int givenPosition);
  
  public boolean removeNo(int givenPosition);
  
  public int indexOf(T anEntry);
  
  public boolean replace(T oldEntry, T newEntry);
}