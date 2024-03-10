package net.frnk6655.stalkercreepers.mixin;

import net.frnk6655.stalkercreepers.StalkerCreeperIgniteGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperMixin implements MobEntityAccessor {
	// Remove the vanilla CreeperIgniteGoal and replace it with StalkerCreeperCreeperIgniteGoal
	// future work: A optimization can be done by using @ModifyArg to change the CreeperIgniteGoal call to StalkerCreeperCreeperIgniteGoal
	@Inject(method = "initGoals", at = @At("TAIL"))
	private void injectInitGoals(CallbackInfo info) {
		CreeperIgniteGoal vanillaCreeperIgniteGoal = null;
		int vanillaPriority = 0;
		GoalSelector goalSelector = this.getGoalSelector();
		for (PrioritizedGoal goal : goalSelector.getGoals()) {
			if (goal.getGoal() instanceof CreeperIgniteGoal creeperIgniteGoal) {
				vanillaPriority = goal.getPriority();
				vanillaCreeperIgniteGoal = creeperIgniteGoal;
				break;
			}
		}

		// if no vanilla CreeperIgniteGoal exit here (other mod must have removed it or something)
		if (vanillaCreeperIgniteGoal != null) {
			goalSelector.remove(vanillaCreeperIgniteGoal);
			goalSelector.add(vanillaPriority, new StalkerCreeperIgniteGoal((CreeperEntity) (Object) this));
			// System.out.println("modified Creeper " + this);
		}
	}
}
