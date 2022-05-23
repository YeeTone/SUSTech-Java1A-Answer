public enum ChessColor {
    BLACK{
        @Override
        public String displayName() {
            return "b";
        }

        @Override
        public ChessColor reverseColor() {
            return WHITE;
        }
    }, WHITE{
        @Override
        public String displayName() {
            return "w";
        }

        @Override
        public ChessColor reverseColor() {
            return BLACK;
        }
    }, NONE{
        @Override
        public String displayName() {
            return "null";
        }

        @Override
        public ChessColor reverseColor() {
            return this;
        }
    };

    public abstract ChessColor reverseColor();

    public abstract String displayName();
}
