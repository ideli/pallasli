/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'Mixky.awsoft');

Mixky.awsoft.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/' + 'portal/administrator/direct';

Mixky.awsoft.POLLING_URLS = {
}

Mixky.awsoft.REMOTING_API = {
  url: Mixky.awsoft.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    CommonLibDirect: [
      {
        name: 'getNewTableRecordId'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    OutlineDirect: [
      {
        name: 'getOutline'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      }
    ],
    DictionaryDirect: [
      {
        name: 'getDictionaryManageList'/*(long) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getDictionaryTree'/*(String) => com.google.gson.JsonArray */,
        len: 1,
        formHandler: false
      },
      {
        name: 'saveDictionary'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    DesignObjectDirect: [
      {
        name: 'deleteObject'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'forceSaveObject'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'renameObject'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'saveObject'/*(String, com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getDesignObjectList'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'loadObject'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'addObject'/*(String, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getAllSubObjectList'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'deleteObjects'/*(com.google.gson.JsonArray) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getSubObjectList'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'pasteObject'/*(String, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      }
    ],
    FlowDesignerDirect: [
      {
        name: 'setFlowLocked'/*(String, boolean) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'updateCaption'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'insertNode'/*(String, String, int, int) => com.google.gson.JsonObject */,
        len: 4,
        formHandler: false
      },
      {
        name: 'getFlowCells'/*(String, boolean) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'insertRoute'/*(String, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'updateRouteTarget'/*(String, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'removeCell'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'saveFlowPosition'/*(String, com.google.gson.JsonObject, com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      }
    ],
    OrganizationDirect: [
      {
        name: 'getRoleManageList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getDeptTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getGjdYhs'/*(String, long, String) => com.google.gson.JsonArray */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getRoleTree'/*(String) => com.google.gson.JsonArray */,
        len: 1,
        formHandler: false
      },
      {
        name: 'saveUserScope'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'saveRole'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getDeptUserTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getExpressionDisplay'/*(String[]) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getGjdYhWds'/*(String, long, String) => com.google.gson.JsonArray */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getOrganizationTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getRoleInfo'/*(int, int, String, String, String) => com.google.gson.JsonObject */,
        len: 5,
        formHandler: false
      },
      {
        name: 'loadSelectedUsers'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getOrganizationTree1'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'loadSelectedExpressions'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getUserDeptManageList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserInfo'/*(int, int, String, String, String) => com.google.gson.JsonObject */,
        len: 5,
        formHandler: false
      },
      {
        name: 'roleChg'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'saveUser'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'roleDel'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getAllUserList'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'saveDept'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getGjds'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getRoles'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getOrgChartData'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getAllUserListPage'/*(int, int, String, String, String) => com.google.gson.JsonObject */,
        len: 5,
        formHandler: false
      },
      {
        name: 'getUserTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getDeptManageList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'userSave'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserBgxx'/*(int, int, String, String, com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 5,
        formHandler: false
      },
      {
        name: 'getDepts'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'deptchk'/*(long, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'SaveDS'/*(long, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'roleSave'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'userDel'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'chkRole'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getExpressionData'/*(String, String) => com.google.gson.JsonObject */,
        len: 2,
        formHandler: false
      },
      {
        name: 'userChg'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getDeptManageList1'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'resetUserPassword'/*(long) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserList1'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'chkUname'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserRoleManageList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    DesktopDirect: [
      {
        name: 'getMenuTree'/*(String, String) => com.google.gson.JsonArray */,
        len: 2,
        formHandler: false
      },
      {
        name: 'saveMenu'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getMenuManageList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'syncPortalData'/*(com.google.gson.JsonObject) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      }
    ],
    AuthorityDirect: [
      {
        name: 'getAllModuleRole'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getAllModuleRoleAuthList'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'saveMenuAuth'/*(long, String, String) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'saveModuleRoleAuth'/*(String, String, String, String) => com.google.gson.JsonObject */,
        len: 4,
        formHandler: false
      },
      {
        name: 'getAllMenu'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      },
      {
        name: 'getAllMenuList'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      }
    ],
    BuilderDirect: [
      {
        name: 'buildJsFile'/*(String) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'syncToApplication'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      }
    ],
    MessagerAppDirect: [
      {
        name: 'syncToIMServer'/*() => com.google.gson.JsonObject */,
        len: 0,
        formHandler: false
      }
    ],
    UserScopeDirect: [
      {
        name: 'getInitPath'/*(long) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getUserScope'/*(long) => com.google.gson.JsonObject */,
        len: 1,
        formHandler: false
      },
      {
        name: 'selectScopeNode'/*(String, int, long) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getUserScopeListPage'/*(int, int, String, String, String) => com.google.gson.JsonObject */,
        len: 5,
        formHandler: false
      },
      {
        name: 'deselectScopeNode'/*(String, int, long) => com.google.gson.JsonObject */,
        len: 3,
        formHandler: false
      },
      {
        name: 'getOutline'/*(String, String, int, long) => com.google.gson.JsonArray */,
        len: 4,
        formHandler: false
      }
    ]
  }
}


Mixky.awsoft.REMOTING_API.timeout = 800000;
Mixky.awsoft.REMOTING_API.maxRetries = 0;
Ext.Direct.addProvider(Mixky.awsoft.REMOTING_API);
