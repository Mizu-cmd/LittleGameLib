package fr.mizu.littlegameslib.game;

public class GameSettings {

    private boolean canBreakBlocks;
    private boolean canPlaceBlocks;
    private boolean canPvP;
    private boolean canDrop;
    private boolean canCraft;
    private boolean canFoodLevelChange;
    private boolean canGetDamage;
    private boolean canMoveInventory;
    private boolean canInteract;
    private boolean countKills;

    public void setCountKills(boolean countKills) {
        this.countKills = countKills;
    }

    public GameSettings(){
        this.canBreakBlocks = true;
        this.canPlaceBlocks = true;
        this.canPvP = true;
        this.canDrop = true;
        this.canCraft = true;
        this.canFoodLevelChange = true;
        this.canGetDamage = true;
        this.canMoveInventory = true;
        this.canInteract = true;
        this.countKills = true;
    }
    public boolean countKills(){ return countKills; }

    public boolean isCanBreakBlocks() {
        return canBreakBlocks;
    }

    public void setCanBreakBlocks(boolean canBreakBlocks) {
        this.canBreakBlocks = canBreakBlocks;
    }

    public boolean isCanPlaceBlocks() {
        return canPlaceBlocks;
    }

    public void setCanPlaceBlocks(boolean canPlaceBlocks) {
        this.canPlaceBlocks = canPlaceBlocks;
    }

    public boolean isCanPvP() {
        return canPvP;
    }

    public void setCanPvP(boolean canPvP) {
        this.canPvP = canPvP;
    }

    public boolean isCanDrop() {
        return canDrop;
    }

    public void setCanDrop(boolean canDrop) {
        this.canDrop = canDrop;
    }

    public boolean isCanCraft() {
        return canCraft;
    }

    public void setCanCraft(boolean canCraft) {
        this.canCraft = canCraft;
    }

    public boolean isCanFoodLevelChange() {
        return canFoodLevelChange;
    }

    public void setCanFoodLevelChange(boolean canFoodLevelChange) {
        this.canFoodLevelChange = canFoodLevelChange;
    }

    public boolean isCanGetDamage() {
        return canGetDamage;
    }

    public void setCanGetDamage(boolean canGetDamage) {
        this.canGetDamage = canGetDamage;
    }

    public boolean isCanMoveInventory() {
        return canMoveInventory;
    }

    public void setCanMoveInventory(boolean canMoveInventory) {
        this.canMoveInventory = canMoveInventory;
    }

    public boolean isCanInteract() {
        return canInteract;
    }

    public void setCanInteract(boolean canInteract) {
        this.canInteract = canInteract;
    };

}