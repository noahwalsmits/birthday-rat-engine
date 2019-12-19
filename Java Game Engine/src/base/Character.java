package base;

import engine.visual.animation.AnimationSet;
import engine.visual.animation.ErrorResources;

import java.util.List;

public class Character {
    private String name;
    private List<AnimationSet> animationSets;
    private AnimationSet currentAnimation;

    public Character(String name) {
        this.name = name;

        AnimationSet errorAnimation = ErrorResources.getInstance().getErrorAnimation();
        this.addAnimationSet(errorAnimation);
        this.currentAnimation = errorAnimation;
    }

    public void addAnimationSet(AnimationSet animationSet) {
        this.animationSets.add(animationSet);
    }

    //TODO: something more efficient
    public void setCurrentAnimation(String animationName) {
        AnimationSet foundAnimation = null;

        for (AnimationSet animationSet : this.animationSets) {
            if (animationSet.getName().equals(animationName)) {
                foundAnimation = animationSet;
            }
        }

        if (!(foundAnimation == null)) {
            this.currentAnimation = foundAnimation;
        }
    }
}
