/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'Mixky.wasoft');

Mixky.wasoft.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/' + 'portal/portal/direct';

Mixky.wasoft.POLLING_URLS = {
}

Mixky.wasoft.REMOTING_API = {
  url: Mixky.wasoft.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    DesktopDirect: [
      {
        name: 'updateMessageState'/*(long) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'infoMsg'/*(com.google.gson.JsonArray, String, int, String, String, String, long) => com.google.gson.JsonObject */,
        len: 7,
        formHandler: false
      },
      {
        name: 'getShortcuts'/*(String) => com.google.gson.JsonArray */,
        len: 1,
        formHandler: false
      },
      {
        name: 'accessToUserInformation'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'deleteWallPaper'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getUserConfig'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getWallpapers'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'saveUserConfig'/*(String, com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'signTrans'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getInfoList'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getBpmTodoMessage'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getSubjects'/*(String) => com.google.gson.JsonArray */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getTodoList'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'delUserConfig'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getQuickStarts'/*(String) => com.google.gson.JsonArray */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getDesktopStyles'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      }
    ],
    OrganizationDirect: [
      {
        name: 'onlineusers'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getDeptTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'loadSelectedExpressions'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getOrgUsersList'/*(int, int, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getDeptUserTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getUserList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'delonlineusers'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getOrganizationTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'SaveDS'/*(long, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getExpressionData1'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getExpressionData'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'changePassword'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getDeptTree1'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'loadSelectedUsers'/*(String, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getUserList1'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getOrganizationTree1'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      }
    ],
    KqglDirect: [
      {
        name: 'kqgl_qd'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'kqgl_qt'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      }
    ]
  }
}


Mixky.wasoft.REMOTING_API.timeout = 800000;
Mixky.wasoft.REMOTING_API.maxRetries = 0;
Ext.Direct.addProvider(Mixky.wasoft.REMOTING_API);
