package is;

import is.mediator.Mediator;

public class Test {
    public static void main(String[] args){
        Mediator m = new Mediator();
        PaginationIF p = new Pagination(m);
    }
}
