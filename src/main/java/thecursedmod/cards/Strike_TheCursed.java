package thecursedmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;

public class Strike_TheCursed extends AbstractTheCursedCard {
	public static final String ID = "Strike_TheCursed";
	public static final	String NAME = "Strike";
	public static final	String IMG = "cards/strike.png";
	public static final	String DESCRIPTION = "Deal !D! damage.";

	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;

	private static final int POOL = 0;

	private static final int COST = 1;
	private static final int POWER = 6;
	private static final int UPGRADE_BONUS = 3;

	public Strike_TheCursed() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.tags.add(BaseModCardTags.BASIC_STRIKE);
		this.tags.add(CardTags.STRIKE);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

	}

	public AbstractCard makeCopy() {
		return new Strike_TheCursed();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
	
}
