package paging;

public class PageRequest implements Pageable {
    private int pageSize;
    private int pageIndex;

    public PageRequest(int pageIndex, int pageSize) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public static PageRequest of(int pageIndex, int pageSize){
        return new PageRequest(pageIndex,pageSize);
    }
    @Override
    public int getPage() {
        return pageIndex;
    }

    @Override
    public int getSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return (pageIndex-1)*pageSize+1;
    }
}
