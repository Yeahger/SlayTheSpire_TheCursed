package thecursedmod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PreMonsterTurnSubscriber;

// TODO: import all The Curse classes
import thecursedmod.cards.Defend_TheCursed;
import thecursedmod.cards.Strike_TheCursed;
import thecursedmod.characters.TheCursed;
import thecursedmod.patches.AbstractCardEnum;
import thecursedmod.patches.TheCursedEnum;


@SpireInitializer
public class TheCursedMod implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber,PostDrawSubscriber,OnStartBattleSubscriber,PreMonsterTurnSubscriber {

	public static final Logger logger = LogManager.getLogger(TheCursedMod.class);

	private static final String MODNAME = "The Cursed - Mod";
	private static final String AUTHOR = "Jimmy Pauphilet";
	private static final String DESCRIPTION = "v0.0.1\n Adds The Cursed character";

	private static final Color CURSED_COLOR = CardHelper.getColor(90.0f, 90.0f, 100.0f);
	private static final String ASSETS_FOLDER = "images";

	private static final String ATTACK_CARD = "512/bg_attack_witch.png";
	private static final String SKILL_CARD = "512/bg_skill_witch.png";
	private static final String POWER_CARD = "512/bg_power_witch.png";

	private static final String ENERGY_ORB = "512/card_witch_orb.png";

	private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_witch.png";
	private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_witch.png";
	private static final String POWER_CARD_PORTRAIT = "1024/bg_power_witch.png";
	private static final String ENERGY_ORB_PORTRAIT = "1024/card_witch_orb.png";

	private static final String CHAR_BUTTON = "charSelect/button.png";
	private static final String CHAR_PORTRAIT = "charSelect/portrait.png";
	public static final String CHAR_SHOULDER_1 = "char/shoulder.png";
	public static final String CHAR_SHOULDER_2 = "char/shoulder2.png";
	public static final String CHAR_CORPSE = "char/corpse.png";
	public static final String CHAR_SKELETON_ATLAS = "char/skeleton.atlas";
	public static final String CHAR_SKELETON_JSON = "char/skeleton.json";

	public static final String BADGE_IMG = "badge.png";

	public static int cardsDrawnTotal = 0;
	public static int cardsDrawnThisTurn = 0;
	public static int cursesDrawnTotal = 0;

	public static final String getResourcePath(String resource) {
		return ASSETS_FOLDER + "/" + resource;
	}

	public TheCursedMod() {
		BaseMod.subscribe(this);

		BaseMod.addColor(AbstractCardEnum.CURSED,
				CURSED_COLOR, CURSED_COLOR, CURSED_COLOR, CURSED_COLOR, CURSED_COLOR, CURSED_COLOR, CURSED_COLOR,
				getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD), getResourcePath(POWER_CARD),
				getResourcePath(ENERGY_ORB),
				getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT), getResourcePath(POWER_CARD_PORTRAIT),
				getResourcePath(ENERGY_ORB_PORTRAIT));
	}

	public static void initialize() {
		logger.info("Initializing The Cursed Mod");
		new TheCursedMod();
	}

	public void receivePostInitialize() {
		Texture badgeTexture = new Texture(getResourcePath(BADGE_IMG));
		ModPanel settingsPanel = new ModPanel();
		settingsPanel.addUIElement(new ModLabel("No settings", 400.0f, 700.0f, settingsPanel, (me) -> {}));
		BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

		Settings.isDailyRun = false;
		Settings.isTrial = false;
		Settings.isDemo = false;
	}


	public void receiveEditCharacters() {
		BaseMod.addCharacter(new TheCursed("The Cursed"),
				getResourcePath(CHAR_BUTTON), 
				getResourcePath(CHAR_PORTRAIT),
				TheCursedEnum.THE_CURSED);
	}


	public void receiveEditRelics() {
//		RelicLibrary.add(new BlackCat());
//		BaseMod.addRelicToCustomPool(new BirdCage(),AbstractCardEnum.WITCH);
	}

	public void receiveEditCards() {
		//BASIC
		BaseMod.addCard(new Strike_TheCursed());
		BaseMod.addCard(new Defend_TheCursed());

		//COMMON
		//Attacks

		//Skills

		//UNCOMMON
		//Attacks

		//Skills

		//Powers


		//RARE
		//Attacks

		//Skills

		//Powers

	}


	public void receiveEditStrings() {
//		String relicStrings = Gdx.files.internal("witchmod_strings/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
//		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
	}


	public void receiveEditKeywords() {
		//String[] persistent = {"persistent"};
		//BaseMod.addKeyword(persistent, "Doesn't get discarded automatically at the end of the turn.");
	}
	
	public void receivePostDraw(AbstractCard c) {
		AbstractPlayer player = AbstractDungeon.player;
		//custom callback for card draw on powers
//        for (AbstractPower p : player.powers) {
//        	if (p instanceof AbstractWitchPower) {
//        		((AbstractWitchPower)p).onCardDraw(c);
//        	}
//        }
        cardsDrawnThisTurn++;
        cardsDrawnTotal++;
        if (c.type == CardType.CURSE) {
        	cursesDrawnTotal++;
        }
	}

	@Override
	public boolean receivePreMonsterTurn(AbstractMonster m) {
		cardsDrawnThisTurn = 0;
		return true;
	}

	@Override
	public void receiveOnBattleStart(AbstractRoom room) {
		cardsDrawnTotal = 0;
		cursesDrawnTotal = 0;
		cardsDrawnThisTurn = 0;
	}
	

}
