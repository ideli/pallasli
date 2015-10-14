/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'Pallas.design.api');

Pallas.design.api.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/' + (window.location.pathname.split('/').length>2 ? window.location.pathname.split('/')[1]+ '/' : '')  + 'djn/directprovider';

Pallas.design.api.POLLING_URLS = {
}

Pallas.design.api.REMOTING_API = {
  url: Pallas.design.api.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    TableDirect: [
      {
        name: 'getTablesF'/*(com.pallas.designer.bean.Table) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getTableColumns'/*(com.pallas.designer.bean.TableField) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getPanels'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'saveTableFields'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getOracleTableColumns'/*(com.pallas.designer.bean.TableField) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getFieldPropetyValues'/*(com.pallas.designer.bean.TableFieldConfig) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getFieldSets'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getAllFields'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getTables'/*(com.pallas.designer.bean.Table) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    PageDirect: [
      {
        name: 'savePage'/*(com.pallas.designer.bean.Page) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getPages'/*(com.pallas.designer.bean.Page) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getPageFields'/*(com.pallas.designer.bean.Page) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    KnowledgeAction: [
      {
        name: 'addKnowledgeType'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      },
      {
        name: 'alterKnowledgeType'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      },
      {
        name: 'changeKnowledge'/*(com.google.gson.JsonArray) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'loadKnowledgeType'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      }
    ],
    FieldSetDirect: [
      {
        name: 'saveFieldSet'/*(com.pallas.designer.bean.Fieldset) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getFieldSetFields'/*(com.pallas.designer.bean.Fieldset) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getFieldSets'/*(com.pallas.designer.bean.Fieldset) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    WordAction: [
      {
        name: 'addWordType'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      },
      {
        name: 'loadWordType'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      },
      {
        name: 'alterWordType'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      }
    ],
    TreeDirect: [
      {
        name: 'getOracleTree'/*(int, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getTree'/*(int, boolean, String) => com.google.gson.JsonArray */,
        len: 3,
        formHandler: false
      }
    ],
    SysDirect: [
      {
        name: 'saveCompType'/*(com.pallas.designer.bean.CompConfig) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getCompTypes'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      }
    ]
  }
}

