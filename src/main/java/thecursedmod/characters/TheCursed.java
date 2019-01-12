package thecursedmod.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import thecursedmod.TheCursedMod;
import thecursedmod.cards.ZombieSpit;
import thecursedmod.patches.AbstractCardEnum;
import thecursedmod.patches.TheCursedEnum;


public class TheCursed extends CustomPlayer {

    public static final int ENERGY_PER_TURN = 3;

    public static final String[] orbTextures = {
            TheCursedMod.getResourcePath("char/orb/layer1.png"),
            TheCursedMod.getResourcePath("char/orb/layer2.png"),
            TheCursedMod.getResourcePath("char/orb/layer3.png"),
            TheCursedMod.getResourcePath("char/orb/layer4.png"),
            TheCursedMod.getResourcePath("char/orb/layer5.png"),
            TheCursedMod.getResourcePath("char/orb/layer6.png"),
            TheCursedMod.getResourcePath("char/orb/layer1d.png"),
            TheCursedMod.getResourcePath("char/orb/layer2d.png"),
            TheCursedMod.getResourcePath("char/orb/layer3d.png"),
            TheCursedMod.getResourcePath("char/orb/layer4d.png"),
            TheCursedMod.getResourcePath("char/orb/layer5d.png")
    };

    public TheCursed(String name) {
        super(name, TheCursedEnum.THE_CURSED, orbTextures, TheCursedMod.getResourcePath("char/orb/vfx.png"), null, new SpriterAnimation(TheCursedMod.getResourcePath("char/spriter/witch.scml")));
        dialogX = drawX + 0.0f * Settings.scale;
        dialogY = drawY + 220.0f * Settings.scale;

        initializeClass(null, TheCursedMod.getResourcePath(TheCursedMod.CHAR_SHOULDER_2),
                TheCursedMod.getResourcePath(TheCursedMod.CHAR_SHOULDER_1),
                TheCursedMod.getResourcePath(TheCursedMod.CHAR_CORPSE),
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
    }

    @Override
    public void applyEndOfTurnTriggers() {
        for (AbstractPower p : powers) {
            p.atEndOfTurn(true);
        }
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Strike_Witch");
        retVal.add("Strike_Witch");
        retVal.add("Strike_Witch");
        retVal.add("Strike_Witch");
        retVal.add("Defend_Witch");
        retVal.add("Defend_Witch");
        retVal.add("Defend_Witch");
        retVal.add("Defend_Witch");
        // TODO : add other starter cards
        return retVal;
        //return getAllCards();
    }

    //for debug
    private static ArrayList<String> getAllCards() {
        ArrayList<String> out = new ArrayList<String>();
        for (AbstractCard card : BaseMod.getCustomCardsToAdd()) {
            out.add(card.cardID);
        }
        return out;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
//        retVal.add("BlackCat");
//        UnlockTracker.markRelicAsSeen("BlackCat");

        // TODO : add starter relic
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        // TODO : add translation
        return new CharSelectInfo("The Cursed", "He is specialized NL in dealing with curses.",
                67, 67, 0, 99, 5,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass var1) {
        return "The Cursed";
    }

    @Override
    public CardColor getCardColor() {
        return AbstractCardEnum.CURSED;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        // TODO : change this card
        return new ZombieSpit();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.SLATE;
    }

    @Override
    public String getLeaderboardCharacterName() {
        return "The Cursed";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }


    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("BYRD_DEATH", MathUtils.random(-0.2f, 0.2f));
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "BYRD_DEATH";
    }


    @Override
    public String getLocalizedCharacterName() {
        return "The Cursed";
    }


    @Override
    public AbstractPlayer newInstance() {
        return new TheCursed(this.name);
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.SLATE;
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[5];
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.SLATE;
    }

    @Override
    public String getSpireHeartText() {
        return "NL You invoke an ominous curse...";
    }

    @Override
    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_VERTICAL};
    }
}
