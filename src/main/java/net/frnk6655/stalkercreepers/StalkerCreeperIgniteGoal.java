package net.frnk6655.stalkercreepers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.Vec3d;

public class StalkerCreeperIgniteGoal extends CreeperIgniteGoal{
    private final CreeperEntity creeper;

    public StalkerCreeperIgniteGoal(CreeperEntity creeper) {
        super(creeper);
        this.creeper = creeper;
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = creeper.getTarget();
        return creeper.getFuseSpeed() > 0 || livingEntity != null && creeper.squaredDistanceTo(livingEntity) < 9.0 && isInVictimsView(creeper, livingEntity);
    }

    private boolean isInVictimsView(CreeperEntity stalker, LivingEntity victim) {
        // this code is a variation of EndermanEntity.isPlayerStaring, if it has to be remade
        Vec3d visionVec = victim.getRotationVec(1.0F).normalize();
        Vec3d targetVec = new Vec3d(stalker.getX() - victim.getX(),
                stalker.getBoundingBox().minY + (double) (stalker.getHeight() / 2.0F) - (victim.getY() + (double) victim.getStandingEyeHeight()),
                stalker.getZ() - victim.getZ()).normalize();

        boolean inFOV = visionVec.dotProduct(targetVec) > 0.1 && victim.canSee(stalker);
        // System.out.println("dotProduct result in isInVictimsView: " + dotProduct + "; inFOV: " + inFOV);
        return inFOV;
    }
}
