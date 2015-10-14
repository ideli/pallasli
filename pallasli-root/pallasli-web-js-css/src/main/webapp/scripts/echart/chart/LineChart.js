Ext.define('Pallas.chart.LineChart', {
	mixins: {
        observable: 'Ext.util.Observable'
    },
	constructor: function (config) {
        this.mixins.observable.constructor.call(this, config);
        this.init();
    },

    init: Ext.emptyFn
});