package is;

import is.mediator.Mediator;
import is.state.Pagination;
import is.state.PaginationIF;

public class Test {
    public static void main(String[] args){
        Mediator m = new Mediator();
        PaginationIF p = new Pagination(m);
    }
}
