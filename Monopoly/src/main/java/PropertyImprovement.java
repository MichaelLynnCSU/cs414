public class PropertyImprovement {

    public Property property;

    public PropertyImprovement() {}

    public PropertyImprovement(Property property) {
        this.property = property;
    }

    public boolean[] buyImprovement(Player player) {
        player.subtractMoney(getUpgradePrice());
        boolean[] values = property.upgrade();
        return values;
    }

    private int getUpgradePrice() {
        int price = property.value;

        if(price<=120) {
            return 50;
        }
        else if(price<=200) {
            return 100;
        }
        else if(price<=280) {
            return 150;
        }
        else {
            return 200;
        }
    }
}
