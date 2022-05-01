package Spring2022A4;

public enum SortBy {
    PurchaseTime {
        @Override
        public int compare(Product p1, Product p2) {
            return Integer.compare(p1.getPurchasedTime(), p2.getPurchasedTime());
        }
    }, Rating {
        @Override
        public int compare(Product p1, Product p2) {
            int compare = Float.compare(p1.getAvgRating(), p2.getAvgRating());
            return compare != 0 ? compare : PurchaseTime.compare(p1, p2);
        }
    }, Price {
        @Override
        public int compare(Product p1, Product p2) {
            int compare = Float.compare(p1.getPrice(), p2.getPrice());
            return compare != 0 ? compare : PurchaseTime.compare(p1, p2);
        }
    };

    public abstract int compare(Product p1, Product p2);
}