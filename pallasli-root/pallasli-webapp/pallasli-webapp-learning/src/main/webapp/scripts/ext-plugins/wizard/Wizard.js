Ext.ux.Wiz = Ext.extend(Ext.Window, {

	height : 420,
	width : 600,
	closable : true,
	resizable : true,
	modal : true,
	cards : null,
	previousButtonText : BizFuse.message['action.pre'],
	nextButtonText : BizFuse.message['action.next'],
	cancelButtonText : BizFuse.message['confirm.cancel'],
	finishButtonText : BizFuse.message['action.finish'],
	headerConfig : {},
	cardPanelConfig : {},
	previousButton : null,
	nextButton : null,
	cancelButton : null,
	cardPanel : null,
	currentCard : -1,
	headPanel : null,
	cardCount : 0,
	cardsValues : {},

	initComponent : function() {
		this.initButtons();
		this.initPanels();

		var title = this.title || this.headerConfig.title;
		title = title || "";

		Ext.apply(this, {
			title : title,
			layout : 'border',
			cardCount : this.cards.length,
			buttons : [this.previousButton, this.nextButton, this.cancelButton],
			items : [this.headPanel, this.cardPanel]
		});

		this.addEvents('cancel', 'finish');

		Ext.ux.Wiz.superclass.initComponent.call(this);
	},

	getWizardData : function() {
		var cards = this.cards;
		for (var i = 0, len = cards.length; i < len; i++) {
			if (cards[i]) {
				this.cardsValues[i] = cards[i].getData();
			} else {
				this.cardsValues[i] = {};
			}
		}

		return cardsValues;
	},

	initEvents : function() {
		Ext.ux.Wiz.superclass.initEvents.call(this);
	},

	initPanels : function() {
		var cards = this.cards;
		var cardPanelConfig = this.cardPanelConfig;
		
		for (var i = 0, len = cards.length; i < len; i++) {
			cards[i].on('show', this.onCardShow, this);
		}

		Ext.apply(this.headerConfig, {
					steps : cards.length
				});

		this.headPanel = new Ext.ux.Wiz.Header(this.headerConfig);

		Ext.apply(cardPanelConfig, {
					region : 'center',
					border : false,
					activeItem : 0,
					layout : 'card',
					defaults : {
						border : false
					},
					items : cards
				});

		this.cardPanel = new Ext.Panel(cardPanelConfig);
	},

	initButtons : function() {
		this.previousButton = new Ext.Button({
					text : this.previousButtonText,
					disabled : true,
					minWidth : 75,
					handler : this.onPreviousClick,
					scope : this
				});

		this.nextButton = new Ext.Button({
					text : this.nextButtonText,
					minWidth : 75,
					handler : this.onNextClick,
					scope : this
				});

		this.cancelButton = new Ext.Button({
					text : this.cancelButtonText,
					handler : this.onCancelClick,
					scope : this,
					minWidth : 75
				});
	},

	onCardShow : function(card) {
		var parent = card.ownerCt;

		var items = parent.items;

		for (var i = 0, len = items.length; i < len; i++) {
			if (items.get(i).id == card.id) {
				break;
			}
		}

		this.currentCard = i;
		this.headPanel.updateStep(i, card.text);
		

		if (i == len - 1) {
			this.nextButton.setText(this.finishButtonText);
		} else {
			this.nextButton.setText(this.nextButtonText);
		}

		if (i == 0) {
			this.previousButton.setDisabled(true);
		} else {
			this.previousButton.setDisabled(false);
		}
		
		card.setData();

	},

	onCancelClick : function() {
		if (this.fireEvent('cancel', this) !== false) {
			this.close();
		}
	},

	onFinish : function() {
		if (this.cards[this.currentCard].finish()) {
			this.cardsValues[this.currentCard] = this.cards[this.currentCard].getData();
			this.close();
		}
	},

	onPreviousClick : function() {
		if (this.currentCard > 0) {
			this.cardPanel.getLayout().setActiveItem(this.currentCard - 1);
		} else {
			return false;
		}
	},

	onNextClick : function() {
		if (this.cards[this.currentCard].finish()) {
			if (this.currentCard == this.cardCount - 1) {
				this.onFinish();
			} else {
				this.cardsValues[this.currentCard] = this.cards[this.currentCard].getData();
				this.cardPanel.getLayout().setActiveItem(this.currentCard + 1);
			}
		}
	}
});