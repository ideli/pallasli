/*!
* 文件模型
*/

Ext.define('MyDesktop.components.FileModel', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'text' },
             { name: 'urlType' },
             { name: 'url' },
             { name: 'preImg' },
             {name: 'size', type: 'float'},
             {name:'lastmod', type:'date', dateFormat:'timestamp'}
    ]
});