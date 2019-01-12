package thecursedmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;

public class Defend_TheCursed extends AbstractTheCursedCard {
	public static final String ID = "Defend_TheCursed";
	public static final	String NAME = "Defend";
	public static final	String IMG = "cards/defend.png";
	public static final	String DESCRIPTION = "Gain !B! block.";

	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 0;

	private static final int COST = 1;
	private static final int POWER = 5;
	private static final int UPGRADE_BONUS = 3;



	public Defend_TheCursed() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
		this.tags.add(BaseModCardTags.BASIC_DEFEND);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
	}

	public AbstractCard makeCopy() {
		return new Defend_TheCursed();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
