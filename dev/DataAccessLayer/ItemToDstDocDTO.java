package DataAccessLayer;

public class ItemToDstDocDTO {

    private int docNumber;
    private int item;
    private ItemToDstDocController controller;

    public ItemToDstDocDTO(int docNumber,int item)
    {
        this.docNumber=docNumber;
        this.item=item;
        this.controller=new ItemToDstDocController();
    }

    public int getDocNumber() {
        return docNumber;
    }

    public int getItem() {
        return item;
    }

    public boolean add()
    {
        return this.controller.add(this);
    }

    public boolean remove()
    {
        return this.controller.remove(this);
    }
}
