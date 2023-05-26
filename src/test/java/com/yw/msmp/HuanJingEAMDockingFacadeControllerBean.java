//package com.yw.msmp;
//
//import com.kingdee.bos.*;
//import com.kingdee.bos.dao.IObjectPK;
//import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
//import com.kingdee.bos.metadata.entity.FilterInfo;
//import com.kingdee.bos.metadata.entity.FilterItemInfo;
//import com.kingdee.bos.metadata.query.util.CompareType;
//import com.kingdee.bos.util.BOSUuid;
//import com.kingdee.eas.base.permission.UserFactory;
//import com.kingdee.eas.base.permission.UserInfo;
//import com.kingdee.eas.basedata.assistant.*;
//import com.kingdee.eas.basedata.master.cssp.SupplierCollection;
//import com.kingdee.eas.basedata.master.cssp.SupplierFactory;
//import com.kingdee.eas.basedata.master.material.*;
//import com.kingdee.eas.basedata.org.*;
//import com.kingdee.eas.basedata.scm.common.BizTypeCollection;
//import com.kingdee.eas.basedata.scm.common.BizTypeFactory;
//import com.kingdee.eas.basedata.scm.common.RowTypeCollection;
//import com.kingdee.eas.basedata.scm.common.RowTypeFactory;
//import com.kingdee.eas.basedata.scm.sm.pur.DemandTypeCollection;
//import com.kingdee.eas.basedata.scm.sm.pur.DemandTypeFactory;
//import com.kingdee.eas.common.EASBizException;
//import com.kingdee.eas.custom.eammiddle.PurOrderSynInfo;
//import com.kingdee.eas.custom.eammiddle.PurRequestTSynFactory;
//import com.kingdee.eas.custom.eammiddle.PurRequestTSynInfo;
//import com.kingdee.eas.custom.eamorg.EamOrgCollection;
//import com.kingdee.eas.custom.eamorg.EamOrgFactory;
//import com.kingdee.eas.custom.eamorg.EamOrgInfo;
//import com.kingdee.eas.fdc.invite.InviteTypeCollection;
//import com.kingdee.eas.fdc.invite.InviteTypeFactory;
//import com.kingdee.eas.scm.im.inv.MaterialReqBillFactory;
//import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
//import com.kingdee.eas.scm.im.inv.PurInWarehsBillFactory;
//import com.kingdee.eas.scm.im.inv.PurInWarehsBillInfo;
//import com.kingdee.eas.scm.sm.pur.PurOrderEntryCollection;
//import com.kingdee.eas.scm.sm.pur.PurOrderEntryInfo;
//import com.kingdee.eas.scm.sm.pur.PurOrderFactory;
//import com.kingdee.eas.scm.sm.pur.PurOrderInfo;
//import com.kingdee.eas.util.app.DbUtil;
//import com.kingdee.jdbc.rowset.IRowSet;
//import org.apache.log4j.Logger;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class HuanJingEAMDockingFacadeControllerBean extends
//        AbstractHuanJingEAMDockingFacadeControllerBean {
//    private static Logger logger = Logger
//            .getLogger( "com.kingdee.eas.custom.hjeam.HuanJingEAMDockingFacadeControllerBean" );
//
//    /**
//     * 物料新增导入
//     */
//    @Override
//    protected String _addMaterial( Context ctx ) throws BOSException,
//            EASBizException {
//        System.out.println( "物料新增导入" );
//
//        // TODO Auto-generated method stub
//        // super._addMaterial(ctx);
//        OrgUnitInfo s = new CtrlUnitInfo( );
//        s.setId( BOSUuid.read( "qYEX3HscTZKDwgQYxndYgcznrtQ=" ) );
//        ctx.put( "CurOU", s );
//        // 获取物料中间表数据并新增
//        String sql = "select description,createdate,statusdate,status,srcodenum,brand,reason,remark,siteid from
//        T_eam_materialapply";
//        IRowSet rows = DbUtil.executeQuery( ctx, sql );
//        String number = null;
//        try {
//            while ( rows.next( ) ) {
//
//                // 获取物料对象
//                MaterialInfo materialInfo = new MaterialInfo( );
//                materialInfo.put( "isImport", true );
//                //materialInfo.setId(BOSUuid.create(materialInfo.getBOSType()));
//                materialInfo.setNumber( rows.getString( "srcodenum" ) );// 编码
//                number = rows.getString( "srcodenum" );
//                String name = rows.getString( "brand" );
//                if ( name == null || "".equals( name ) ) {
//                    System.out.println( "名称不能为空" );
//                    throw new BOSException( "名称不能为空" );
//                }
//                materialInfo.setName( name );// 名称
//                if ( rows.getString( "remark" ) == null ) {
//                    System.out.println( "计量单位编码不能为空" );
//                    throw new BOSException( "计量单位编码不能为空" );
//                }
//                // 过滤f7
//                EntityViewInfo entityView1 = new EntityViewInfo( );
//                FilterInfo filter1 = new FilterInfo( );
//                filter1.getFilterItems( ).add(
//                        new FilterItemInfo( "number", rows.getString( "remark" ),
//                                CompareType.EQUALS ) );
//                entityView1.setFilter( filter1 );
//                MeasureUnitCollection measureUnitCollection = MeasureUnitFactory
//                        .getLocalInstance( ctx ).getMeasureUnitCollection(
//                                entityView1 );
//                if ( measureUnitCollection.get( 0 ) == null ) {
//                    System.out.println( "计量单位编码不存在" );
//                    throw new BOSException( "计量单位编码不存在" );
//                }
//                materialInfo.setBaseUnit( measureUnitCollection.get( 0 ) );// 基本计量单位
//                // 物料基本分类对象
//                if ( rows.getString( "siteid" ) == null ) {
//                    System.out.println( "物料分类编码不能为空" );
//                    throw new BOSException( "物料分类编码不能为空" );
//                }
//                EntityViewInfo entityView = new EntityViewInfo( );
//                FilterInfo filter = new FilterInfo( );
//                filter.getFilterItems( ).add(
//                        new FilterItemInfo( "number", rows.getString( "siteid" ),
//                                CompareType.EQUALS ) );
//                entityView.setFilter( filter );
//                MaterialGroupCollection materialGroupCollection = MaterialGroupFactory
//                        .getLocalInstance( ctx ).getMaterialGroupCollection(
//                                entityView );
//                if ( materialGroupCollection.get( 0 ) == null ) {
//                    System.out.println( "物料分类编码不存在" );
//                    throw new BOSException( "物料分类编码不存在" );
//                }
//                materialInfo.setMaterialGroup( materialGroupCollection.get( 0 ) );// 物料基本分类
//                materialInfo.setDescription( rows.getString( "description" ) );// 描述
//                String status = rows.getString( "status" );// 状态
//                if ( status.endsWith( "1" ) ) {
//                    materialInfo.setStatus( UsedStatusEnum.APPROVED );
//                } else if ( status.endsWith( "2" ) ) {
//                    materialInfo.setStatus( UsedStatusEnum.FREEZED );
//                } else if ( status.endsWith( "0" ) ) {
//                    materialInfo.setStatus( UsedStatusEnum.UNAPPROVE );
//                } else if ( status.endsWith( "3" ) ) {
//                    materialInfo.setStatus( UsedStatusEnum.DELETED );
//                } else {
//                    System.out.println( "物料状态不正确" );
//                    throw new BOSException( "物料状态不正确" );
//                }
//                materialInfo.setShortName( rows.getString( "remark" ) );// 简称
//                materialInfo.setModel( rows.getString( "siteid" ) );// 规格型号
//
//                materialInfo.setEffectedStatus( 1 );// 生效状态（暂存或保存）
//                // 添加到物料
//                IObjectPK pk = MaterialFactory.getLocalInstance( ctx ).addnew(
//                        materialInfo );
//                pk.toString( );
//            }
//        } catch ( SQLException e ) {
//            // TODO Auto-generated catch block
//            e.printStackTrace( );
//        }
//        return number;
//    }
//
//    /**
//     * 采购订单导出
//     */
//    @Override
//    protected void _purOrderImport( Context ctx ) throws BOSException,
//            EASBizException {
//        // TODO Auto-generated method stub
//        System.out.println( "采购订单导入到中间表facade开始执行" );
//        String sql = "/*dialect*/ select * from T_SM_PurOrder where Fbasestatus=4 and  (cfissynmediatetable is
//        null or cfissynmediatetable <>1) and fid in(select distinct  FPARENTID from T_SM_PURORDERENTRY where
//        cfpurrequestmiddletableid is not null)";
//
//        try {
//            System.out.println( "查询采购订单中已审批且的未同步到中间表中的数据" );
//            System.out.println( sql );
//            IRowSet rows = DbUtil.executeQuery( ctx, sql );
//            System.out.println( "查询采购订单中已审批且的未同步到中间表中的数据查询成功，共" + rows.size( )
//                    + "张采购订单" );
//            while ( rows.next( ) ) {
//                String purorderID = rows.getString( "Fid" );
//
//                PurOrderInfo model1 = PurOrderFactory
//                        .getLocalInstance( ctx )
//                        .getPurOrderInfo( "where id='" + purorderID.trim( ) + "'" );
//
//                // 单据编号 FNumber
//                String Number = model1.getNumber( );
//                // 合同号
//                String wafCore = model1.getWafCore( );
//                if ( "null".equals( wafCore ) ) {
//                    wafCore = "";
//                }
//                // 服务订单
//                Object object = model1.get( "isServiceOrders" );
//                String serviceOrders = "0";
//                if ( "true".equals( object.toString( ) ) ) {
//                    serviceOrders = "1";
//                }
//                // 订单日期 CFBizDate
//                Date BizDate1 = model1.getBizDate( );
//                // SimpleDateFormat formatter = new
//                // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                SimpleDateFormat formatter = new SimpleDateFormat( "yyyy/MM/dd" );
//                String BizDate = formatter.format( BizDate1 );
//                System.out.print( "订单日期：" + BizDate );
//                // BizDate="TO_DATE('"+BizDate+"','yyyy-mm-dd hh24:mi:ss')";
//
//                // 业务类型 CFBizType
//                BOSUuid BizTypeID = model1.getBizType( ).getId( );
//                EntityViewInfo viewInfo = new EntityViewInfo( );
//                FilterInfo filter = new FilterInfo( );
//                filter.getFilterItems( ).add(
//                        new FilterItemInfo( "id", BizTypeID.toString( ) ) );
//                viewInfo.setFilter( filter );
//                BizTypeCollection BizTypeCollection = BizTypeFactory
//                        .getLocalInstance( ctx ).getBizTypeCollection( viewInfo );
//                String BizType = BizTypeCollection.get( 0 ).getNumber( );
//                // 摘要 Fdescription_l2
//                String Fdescription = model1.getDescription( );
//                if ( Fdescription != null ) {
//                    Fdescription = Fdescription.replace( "'", "''" );
//                } else {
//                    Fdescription = "";
//                }
//                // 供应商 CFSupplier
//                BOSUuid SupplierID = model1.getSupplier( ).getId( );
//                viewInfo = new EntityViewInfo( );
//                filter = new FilterInfo( );
//                filter.getFilterItems( ).add(
//                        new FilterItemInfo( "id", SupplierID.toString( ) ) );
//                viewInfo.setFilter( filter );
//                SupplierCollection SupplierCollection = null;
//                try {
//
//                    SupplierCollection = SupplierFactory.getLocalInstance( ctx )
//                            .getSupplierCollection( viewInfo );
//                } catch ( Exception e ) {
//                    System.out.print( "编码为：" + Number + "的采购订单中供应商找不到，供应商id："
//                            + SupplierID.toString( ) );
//                    continue;
//                }
//                String Supplier = SupplierCollection.get( 0 ).getNumber( );
//
//                // 采购组织 CFPurchaseOrgUnit
//                BOSUuid PurchaseOrgUnitID = model1.getPurchaseOrgUnit( ).getId( );
//                viewInfo = new EntityViewInfo( );
//                filter = new FilterInfo( );
//                filter.getFilterItems( ).add(
//                        new FilterItemInfo( "id", PurchaseOrgUnitID.toString( ) ) );
//                viewInfo.setFilter( filter );
//                PurchaseOrgUnitCollection PurchaseOrgUnitCollection = PurchaseOrgUnitFactory
//                        .getLocalInstance( ctx ).getPurchaseOrgUnitCollection(
//                                viewInfo );
//                String EASorg = PurchaseOrgUnitCollection.get( 0 ).getNumber( );
//                EamOrgInfo eamOrgInfo = null;
//                try {
//
//                    eamOrgInfo = EamOrgFactory.getLocalInstance( ctx )
//                            .getEamOrgInfo(
//                                    "where EASOrgNumber ='" + EASorg + "'" );
//                } catch ( BOSException e ) {
//                    System.out.print( "找不到eas组织编码为：" + EASorg
//                            + "的组织对应关系或有多条相同对应关系" );
//                    continue;
//                    // throw new
//                    // BOSException("找不到eas组织编码为："+EASorg+"的组织对应关系或有多条相同对应关系");
//
//                }
//
//                String PurchaseOrgUnit = eamOrgInfo.getEAMOrgNumber( );
//
//                // 币别 CFCurrency
//                BOSUuid CurrencyID = model1.getCurrency( ).getId( );
//                viewInfo = new EntityViewInfo( );
//                filter = new FilterInfo( );
//                filter.getFilterItems( ).add(
//                        new FilterItemInfo( "id", CurrencyID.toString( ) ) );
//                viewInfo.setFilter( filter );
//                CurrencyCollection CurrencyCollection = CurrencyFactory
//                        .getLocalInstance( ctx ).getCurrencyCollection( viewInfo );
//                String Currency = CurrencyCollection.get( 0 ).getNumber( );
//
//                // 汇率 CFExchangeRate
//                String ExchangeRate = model1.getExchangeRate( ).toString( );
//                // 合同单据 FcontractbillID
//                // BOSUuid contractbillID=model1.getContractBill().getId();
//                // viewInfo = new EntityViewInfo();
//                // filter=new FilterInfo();
//                // filter.getFilterItems().add(new FilterItemInfo("id",
//                // contractbillID+.toString()));
//                // viewInfo.setFilter(filter);
//                // PurchaseOrgUnitCollection
//                // PurchaseOrgUnitCollection=PurchaseOrgUnitFactory
//                // .getLocalInstance
//                // (ctx).getPurchaseOrgUnitCollection(viewInfo);
//                // String
//                // PurchaseOrgUnit=PurchaseOrgUnitCollection.get(0).getNumber();
//                // 创建人 Fcreator
//                String Creator = model1.getCreator( ).getId( ).toString( );
//                UserInfo userInfo = UserFactory.getLocalInstance( ctx )
//                        .getUserInfo(
//                                new ObjectUuidPK( model1.getCreator( ).getId( )
//                                        .toString( ) ) );
//                String CreatorName = userInfo.getName( );
//                // 最后修改人 flastupdateuser
//                String LastUpdateUser = model1.getLastUpdateUser( ).getId( )
//                        .toString( );
//                // 获取分录
//                PurOrderEntryCollection PurOrderEntryCollection = model1
//                        .getEntries( );
//
//                for ( int i1 = 0; i1 < PurOrderEntryCollection.size( ); i1++ ) {
//
//                    PurOrderEntryInfo PurOrderEntryInfo = PurOrderEntryCollection
//                            .get( i1 );
//
//                    // //采购申请中间表fid 如果采购订单分录中没有采购申请中间表ID，则不把该分录信息插入采购订单中间表中
//                    String purrequestsynID = "";
//                    try {
//
//                        purrequestsynID = PurOrderEntryInfo.get(
//                                "purRequestMiddleTableID" ).toString( );
//                    } catch ( Exception e ) {
//                        if ( purrequestsynID == "" || purrequestsynID == "0" ) {
//                            System.out.println( "编码：" + Number
//                                    + "的采购订单的分录中没有采购申请中间表id" );
//                            continue;
//                        }
//
//                    }
//                    // BOSUuid Entryid= PurOrderEntryInfo.getId();
//
//                    // id fid
//                    BOSUuid create = BOSUuid.create( new PurOrderSynInfo( )
//                            .getBOSType( ) );
//                    String id = create.toString( );
//
//                    // 物料编码 CFSupplierMaterialNumber
//                    BOSUuid Materialid = PurOrderEntryInfo.getMaterial( )
//                            .getId( );
//                    viewInfo = new EntityViewInfo( );
//                    filter = new FilterInfo( );
//                    filter.getFilterItems( ).add(
//                            new FilterItemInfo( "id", Materialid.toString( ) ) );
//                    viewInfo.setFilter( filter );
//                    MaterialCollection Material = null;
//                    try {
//
//                        Material = MaterialFactory.getLocalInstance( ctx )
//                                .getMaterialCollection( viewInfo );
//                    } catch ( Exception e ) {
//                        System.out.println( "编码：" + Number + "的采购订单的物料不存在，物料id："
//                                + Materialid.toString( ) );
//                        continue;
//                    }
//
//                    /**
//                     * 物料的别名中有值则说明该物料使用的是历史数据，需要把别名中的数据作为EAM物料编码传到中间表
//                     * 若物料的别名中没有值说明物料使用的是通过EAM新增的物料，物料的编码就是EAM的编码
//                     */
//                    String SupplierMaterialNumber = null;
//                    // if(Material.get(0).getAlias()!=null){
//                    // SupplierMaterialNumber=Material.get(0).getAlias();
//                    // }else{
//                    SupplierMaterialNumber = Material.get( 0 ).getNumber( );
//                    // }
//                    /**
//                     * 物料编码去点
//                     */
//                    // System.out.println("物料编码去点前"+SupplierMaterialNumber);
//                    // String[] split = SupplierMaterialNumber.split("\\.");
//                    // for (int i=0;i<split.length;i++){
//                    // SupplierMaterialNumber=SupplierMaterialNumber+split[i];
//                    // }
//                    // System.out.println("物料编码去点后"+SupplierMaterialNumber);
//                    /**
//                     * 去点完成
//                     */
//                    // 计量单位 CFUnit
//                    String UnitID = PurOrderEntryInfo.getUnit( ).getId( )
//                            .toString( );
//                    viewInfo = new EntityViewInfo( );
//                    filter = new FilterInfo( );
//                    filter.getFilterItems( ).add(
//                            new FilterItemInfo( "id", UnitID ) );
//                    viewInfo.setFilter( filter );
//                    MeasureUnitCollection MeasureUnitCollection = MeasureUnitFactory
//                            .getLocalInstance( ctx ).getMeasureUnitCollection(
//                                    viewInfo );
//                    String Unit = MeasureUnitCollection.get( 0 ).getName( );
//                    // 订货数量 CFQty
//                    String Qty = PurOrderEntryInfo.getQty( ).toString( );
//                    // 申请组织 CFRequestOrgUnit
//                    String RequestOrgUnitID = PurOrderEntryInfo
//                            .getRequestOrgUnit( ).getId( ).toString( );
//                    viewInfo = new EntityViewInfo( );
//                    filter = new FilterInfo( );
//                    filter.getFilterItems( ).add(
//                            new FilterItemInfo( "id", RequestOrgUnitID ) );
//                    viewInfo.setFilter( filter );
//                    StorageOrgUnitCollection StorageOrgUnit1 = StorageOrgUnitFactory
//                            .getLocalInstance( ctx ).getStorageOrgUnitCollection(
//                                    viewInfo );
//                    EASorg = StorageOrgUnit1.get( 0 ).getNumber( );
//
//                    EamOrgInfo RequestOrgEamOrgInfo = null;
//                    try {
//
//                        RequestOrgEamOrgInfo = EamOrgFactory.getLocalInstance(
//                                ctx ).getEamOrgInfo(
//                                "where EASOrgNumber ='" + EASorg + "'" );
//                    } catch ( BOSException e ) {
//                        System.out.print( "找不到eas组织编码为：" + EASorg
//                                + "的组织对应关系或有多条相同对应关系" );
//                        continue;
//                        // throw new
//                        //BOSException("找不到eas组织编码为："+EASorg+"的组织对应关系或有多条相同对应关系"
//                        // );
//                        // throw new
//                        //BOSException("找不到eas组织编码为："+EASorg+"的组织对应关系或有多条相同对应关系"
//                        // );
//
//                    }
//
//                    String RequestOrgUnit = RequestOrgEamOrgInfo
//                            .getEAMOrgNumber( );
//                    // 收货组织 CFStorageOrgUnit
//                    String StorageOrgUnitID = PurOrderEntryInfo
//                            .getStorageOrgUnit( ).getId( ).toString( );
//                    viewInfo = new EntityViewInfo( );
//                    filter = new FilterInfo( );
//                    filter.getFilterItems( ).add(
//                            new FilterItemInfo( "id", StorageOrgUnitID ) );
//                    viewInfo.setFilter( filter );
//                    StorageOrgUnitCollection StorageOrgUnit2 = StorageOrgUnitFactory
//                            .getLocalInstance( ctx ).getStorageOrgUnitCollection(
//                                    viewInfo );
//
//                    EASorg = StorageOrgUnit2.get( 0 ).getNumber( );
//
//                    EamOrgInfo StorageOrgEamOrgInfo = null;
//                    try {
//
//                        StorageOrgEamOrgInfo = EamOrgFactory.getLocalInstance(
//                                ctx ).getEamOrgInfo(
//                                "where EASOrgNumber ='" + EASorg + "'" );
//                    } catch ( BOSException e ) {
//                        System.out.print( "找不到eas组织编码为：" + EASorg
//                                + "的组织对应关系或有多条相同对应关系" );
//                        continue;
//                        // throw new
//                        //BOSException("找不到eas组织编码为："+EASorg+"的组织对应关系或有多条相同对应关系"
//                        // );
//                        // throw new
//                        //BOSException("找不到eas组织编码为："+EASorg+"的组织对应关系或有多条相同对应关系"
//                        // );
//
//                    }
//
//                    String StorageOrgUnit = StorageOrgEamOrgInfo
//                            .getEAMOrgNumber( );
//                    // 交货日期 CFDeliveryDate
//                    Date date = PurOrderEntryInfo.getDeliveryDate( );
//                    String DeliveryDate = formatter.format( date );
//                    System.out.print( "交货日期：" + DeliveryDate );
//                    // DeliveryDate="TO_DATE('"+DeliveryDate+
//                    // "','yyyy-mm-dd hh24:mi:ss')";
//                    // 单价 CFPrice
//                    BigDecimal Price = PurOrderEntryInfo.getPrice( );
//                    // 折扣率 CFDiscountRate
//                    BigDecimal DiscountRate = PurOrderEntryInfo
//                            .getDiscountRate( );
//                    // 税率 CFTaxRate
//                    BigDecimal TaxRate = PurOrderEntryInfo.getTaxRate( );
//                    // 含税单价 CFTaxPrice
//                    BigDecimal TaxPrice = PurOrderEntryInfo.getTaxPrice( );
//                    // 折扣额 CFDiscountAmount
//                    BigDecimal DiscountAmount = PurOrderEntryInfo
//                            .getDiscountAmount( );
//                    // 金额 CFAmount
//                    BigDecimal Amount = PurOrderEntryInfo.getAmount( );
//                    // 税额 CFTax
//                    BigDecimal Tax = PurOrderEntryInfo.getTax( );
//                    // 价税合计 CFTaxAmount
//                    BigDecimal TaxAmount = PurOrderEntryInfo.getTaxAmount( );
//                    // 分录备注 CFFremark
//                    String Fremark = "";
//                    if ( PurOrderEntryInfo.getRemark( ) == "null" ) {
//                        Fremark = "";
//                    } else {
//                        Fremark = PurOrderEntryInfo.getRemark( );
//                    }
//                    // 行号
//                    int seq = PurOrderEntryInfo.getSeq( );
//                    // 行类型 CFRowType
//                    String RowTypeID = PurOrderEntryInfo.getRowType( ).getId( )
//                            .toString( );
//                    viewInfo = new EntityViewInfo( );
//                    filter = new FilterInfo( );
//                    filter.getFilterItems( ).add(
//                            new FilterItemInfo( "id", RowTypeID ) );
//                    viewInfo.setFilter( filter );
//                    RowTypeCollection RowTypeCollection = RowTypeFactory
//                            .getLocalInstance( ctx ).getRowTypeCollection(
//                                    viewInfo );
//                    String RowType = RowTypeCollection.get( 0 ).getNumber( );
//                    // BigDecimal RowType=new BigDecimal(110);
//                    // 制单日期 CFCreateDate
//                    Date CreateDate1 = model1.getCreateTime( );
//
//                    String CreateDate = formatter.format( CreateDate1 );
//                    System.out.print( "制单日期：" + CreateDate );
//
//                    // CreateDate="TO_DATE('"+CreateDate+
//                    // "','yyyy-mm-dd hh24:mi:ss')";
//                    //==========================================================
//                    // ================================
//                    // 获取eam单号 申请人 EAM行号
//                    String eamSql = "/*dialect*/select cfeamperson,cfeamnumber,prlineid from
//                    CT_EAM_PurRequestTotalSyn where fid = '"
//                            + purrequestsynID + "'";
//
//                    IRowSet eamRows = DbUtil.executeQuery( ctx, eamSql );
//                    String eamPerson = null;
//                    String eamNumber = null;
//                    String prlineid = null;
//                    while ( eamRows.next( ) ) {
//                        eamPerson = eamRows.getString( "cfeamperson" );
//                        eamNumber = eamRows.getString( "cfeamnumber" );
//                        prlineid = eamRows.getString( "prlineid" );
//                    }
//
//                    //==========================================================
//                    // =====================================
//                    // 物料申请单号 CFMaterialApplyNumber
//                    // String MaterialApplyNumber="";
//                    // 同步标识 ISSYN
//                    // PurOrderSynInfo.setISSYN("Y");
//
//                    // String StorageOrgUnit=PurOrderSynInfo.getCU();
//                    // String synNumber=PurOrderSynInfo.getNumber();
//
//                    String SQL = "";
//
//                    // 执行sql保存或update
//                    StringBuilder sql1 = new StringBuilder( );
//                    sql1.append( "select * from CT_EAM_PurOrderSyn where fID='"
//                            + id.toString( ) + "'" );
//                    IRowSet IRowSet = DbUtil.executeQuery( ctx, sql1.toString( ) );
//                    try {
//                        // update -hanhao
//
//                        if ( IRowSet.size( ) == 0 ) {
//                            SQL = "/*dialect*/ insert into CT_EAM_PURORDERSYN "
//                                    + "(fid,fnumber,CFBizDate,CFBizType,CFSupplier,CFPurchaseOrgUnit,CFCurrency,
//                                    CFExchangeRate,CFSupplierMaterialNumber,CFUnit,CFQty,CFRequestOrgUnit,"
//                                    + "CFStorageOrgUnit,CFDeliveryDate,CFPrice,CFDiscountRate,CFTaxRate,CFTaxPrice,
//                                    CFDiscountAmount,CFAmount,CFTax,CFTaxAmount,"
//                                    + "CFFremark,CFRowType,CFCreateDate,fcreatorid,flastupdateuserid,cfeamperson,
//                                    cfeamnumber,Fsimplename,Fdescription_l2,fname_l2,fseq,prlineid,
//                                    CFContractNumber,cfisserviceOrders,cfApplicant)"
//                                    + "values " + "('"
//                                    + id
//                                    + "','"
//                                    + Number
//                                    + "','"
//                                    + BizDate
//                                    + "','"
//                                    + BizType
//                                    + "','"
//                                    + Supplier
//                                    + "','"
//                                    + PurchaseOrgUnit
//                                    + "','"
//                                    + Currency
//                                    + "','"
//                                    + ExchangeRate
//                                    + "','"
//                                    + SupplierMaterialNumber
//                                    + "','"
//                                    + Unit
//                                    + "','"
//                                    + Qty
//                                    + "','"
//                                    + RequestOrgUnit
//                                    + "','"
//                                    + StorageOrgUnit
//                                    + "','"
//                                    + DeliveryDate
//                                    + "','"
//                                    + Price
//                                    + "','"
//                                    + DiscountRate
//                                    + "','"
//                                    + TaxRate
//                                    + "','"
//                                    + TaxPrice
//                                    + "','"
//                                    + DiscountAmount
//                                    + "','"
//                                    + Amount
//                                    + "','"
//                                    + Tax
//                                    + "','"
//                                    + TaxAmount
//                                    + "','"
//                                    + Fremark
//                                    + "','"
//                                    + RowType
//                                    + "','"
//                                    + CreateDate
//                                    + "','"
//                                    + Creator
//                                    + "','"
//                                    + LastUpdateUser
//                                    + "','"
//                                    + eamPerson
//                                    + "','"
//                                    + eamNumber
//                                    + "','"
//                                    + CreatorName
//                                    + "','"
//                                    + Fdescription
//                                    + "','"
//                                    + Fdescription
//                                    + "','"
//                                    + seq
//                                    + "','"
//                                    + prlineid
//                                    + "','"
//                                    + wafCore
//                                    + "','"
//                                    + serviceOrders
//                                    + "','"
//                                    + PurOrderEntryInfo.get( "applicant" ) + "')";
//                            //==================================================
//                            // ==============================================
//
//                            System.out.println( SQL );
//                            DbUtil.execute( ctx, SQL );
//                            System.out.println( "采购订单中间表插入成功，单据ID:" + id );
//
//                            // 采购订单单据头编码
//                            // 修改采购申请中间表单中的采购订单编号cfpurordernumber
//                            System.out.println( "eam采购申请中间表中采购订单编码字段开始修改" );
//                            String updatepurrequestsyn = "/*dialect*/ update CT_EAM_PurRequestTotalSyn set
//                            cfpurordernumber='"
//                                    + Number
//                                    + "' where fid='"
//                                    + purrequestsynID + "'";
//                            System.out.println( updatepurrequestsyn );
//                            DbUtil.execute( ctx, updatepurrequestsyn );
//                            System.out.println( "eam采购申请中间表中采购订单编码字段修改成功，中间表ID:"
//                                    + purrequestsynID );
//
//                            // 20200305
//
//                            // }else if(IRowSet.size()==1){
//                            //
//                            // SQL=
//                            // "/*dialect*/ update CT_EAM_PURORDERSYN set fnumber='"
//                            // +Number+"',CFBizDate="+BizDate+",CFBizType='"+
//                            // BizType
//                            //+"',CFSupplier='"+Supplier+"',CFPurchaseOrgUnit='"
//                            // +PurchaseOrgUnit+"'" +
//                            // ",CFCurrency='"+Currency+"',CFExchangeRate='"+
//                            // ExchangeRate+"',CFSupplierMaterialNumber='"+
//                            // SupplierMaterialNumber
//                            // +"',CFUnit='"+Unit+"',CFQty='"
//                            // +Qty+"',CFRequestOrgUnit='"+
//                            // RequestOrgUnit+"',"
//                            // +"CFStorageOrgUnit='"+StorageOrgUnit
//                            // +"',CFDeliveryDate="
//                            // +DeliveryDate+",CFPrice='"+Price
//                            // +"',CFDiscountRate='"
//                            // +DiscountRate+"',CFTaxRate='"+TaxRate+
//                            // "',CFTaxPrice='"+TaxPrice+"',CFDiscountAmount='"+
//                            // DiscountAmount
//                            // +"',CFAmount='"+Amount+"',CFTax='"+Tax
//                            // +"',CFTaxAmount='"+TaxAmount+"'," +
//                            // "CFFremark='"+Fremark+"',CFRowType='"+RowType+
//                            // "',CFCreateDate="
//                            // +CreateDate+",fcreatorid='"+Creator
//                            // +"',flastupdateuserid='"
//                            // +LastUpdateUser+"' where FID='"+id+"'";
//                            //
//                            // System.out.println(SQL);
//                            // DbUtil.execute(ctx, SQL);
//                            // System.out.println("采购订单中间表update成功，单据ID:"+id);
//                            //
//                            //
//                            // // //采购申请中间表fid
//                            // String purrequestsynID=PurOrderEntryInfo.get(
//                            // "purRequestMiddleTableID").toString();
//                            // //采购订单单据头编码
//                            // //修改采购申请单中的采购订单编号cfpurordernumber
//                            // System.out.println("eam采购申请中间表中采购订单编码字段开始修改");
//                            // String updatepurrequestsyn=
//                            // "/*dialect*/ update CT_EAM_PurRequestTotalSyn set cfpurordernumber='"
//                            // +Number+"' where fid='"+purrequestsynID+"'";
//                            // System.out.println(updatepurrequestsyn);
//                            // DbUtil.execute(ctx, updatepurrequestsyn);
//                            //System.out.println("eam采购申请中间表中采购订单编码字段修改成功，中间表ID:"
//                            // +purrequestsynID);
//                            //
//                            //
//                            //
//                            //
//                            // }else{
//                            // continue;
//                        }
//                    } catch ( Exception e ) {
//                        System.out.println( "SQL报错：" + e.getLocalizedMessage( ) );
//                        // 删除错误数据
//                        sql = "delete from CT_EAM_PURORDERSYN where fnumber='"
//                                + Number + "'";
//                        DbUtil.execute( ctx, sql );
//                    }
//                    // PurOrderSynFactory.getLocalInstance(ctx).submit(
//                    // PurOrderSynInfo);
//
//                }
//
//                System.out.println( "开始修改采购订单主表中是否已同步到中间表字段" );
//                // update T_SM_PurOrder set cfissynmediatetable=1 where
//                // fid='"+purorderID+"' and exists (select 1 from
//                // CT_EAM_PurRequestTotalSyn set cfpurordernumber='"+Number+"')
//                // String updatesynStatesql=
//                // "/*dialect*/ update T_SM_PurOrder set cfissynmediatetable=1  where fid='"
//                // +purorderID+"'";
//                String updatesynStatesql = "/*dialect*/ update T_SM_PurOrder set cfissynmediatetable=1  where fid='"
//                        + purorderID
//                        + "' and exists (select 1 from CT_EAM_PurRequestTotalSyn where cfpurordernumber='"
//                        + Number + "')";
//                // 反写采购申请表，表示是否从采购申请下推了
//
//                if ( "510b6503-0105-1000-e000-0107c0a812fd463ED552"
//                        .equals( model1.getSourceBillType( ).getId( ).toString( ) ) ) {
//                    String SQL = "/*dialect*/ update T_SM_PurRequest set cfispushdown='1' where fid='"
//                            + PurOrderEntryCollection.get( 0 ).getSourceBillId( )
//                            + "'";
//                    System.out.println( "反写采购申请表中是否下推：" + SQL );
//                    DbUtil.execute( ctx, SQL );
//                }
//                System.out.println( updatesynStatesql );
//                DbUtil.execute( ctx, updatesynStatesql );
//                System.out.println( "采购订单主表中是否已同步到中间表字段修改完成，所修改表id:"
//                        + purorderID );
//
//            }
//        } catch ( Exception e ) {
//            // TODO Auto-generated catch block
//            e.printStackTrace( );
//            System.out.println( "采购订单下推失败：" + e.getMessage( ) );
//        }
//        super._purOrderImport( ctx );
//    }
//
//    /**
//     * 采购申请导入
//     */
//    @Override
//    protected void _purAppliImport( Context ctx ) throws BOSException,
//            EASBizException {
//        System.out.println( "中间表导入到采购申请汇总facade开始执行" );
//        PurRequestTSynInfo purRequestTSynInfo = null;
//
//        String fid = null;
//        // 获取采购申请中间表数据并新增
//        String sql = "/*dialect*/select * from CT_EAM_PurRequestTotalSyn where CFISSYN = 'N' and (CFFailReason is
//        null or CFFailReason = '')";
//
//        IRowSet rows = DbUtil.executeQuery( ctx, sql );
//        System.out.println( "查询中间表未同步采购申请汇总的数据查询成功，共" + rows.size( ) + "条" );
//        try {
//            // 开始遍历
//            while ( rows.next( ) ) {
//                // String failReason= "";
//                // sub——代表这一条整体是否正确
//                boolean sub = true;
//                // i——代表错误次数？
//                int i = 0;
//                StringBuffer sb = new StringBuffer( );
//                purRequestTSynInfo = new PurRequestTSynInfo( );
//
//                // hanhao-add
//                purRequestTSynInfo.put( "applicant", rows
//                        .getString( "CFAPPLICANT" ) );
//                // 2020-03-05
//                // EAM单号非空判断
//                fid = rows.getString( "fid" );
//                purRequestTSynInfo.setCentreID( fid );
//                if ( rows.getString( "cfeamnumber" ) != null
//                        && !"".equals( rows.getString( "cfeamnumber" ) ) ) {
//                    purRequestTSynInfo.setEAMNumber( rows
//                            .getString( "cfeamnumber" ) );// eam 单号
//                } else {
//                    // 反写错误信息
//                    // failReason+= "EAM单号不能为空，请EAM方确认。";
//                    sb.append( "EAM单号不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//                // 申请日期非空判断
//                String biz = rows.getString( "fbizdate" );
//
//                if ( biz != null && !"".equals( biz ) && isDate( ctx, biz ) ) {
//                    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
//                    purRequestTSynInfo.setBizDate( sdf.parse( biz ) );// 申请日期
//                } else {
//                    // 反写错误信息
//                    // failReason+= "申请日期不能为空或日期格式不正确，请EAM方确认。";
//                    sb.append( "申请日期不能为空或日期格式不正确，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//
//                // 财务组织
//                // 财务组织非空
//                if ( rows.getString( "cfcompanyorgunit" ) != null
//                        && !"".equals( rows.getString( "cfcompanyorgunit" ) ) ) {
//                    EamOrgCollection eamOrgCollection = EamOrgFactory
//                            .getLocalInstance( ctx )
//                            .getEamOrgCollection(
//                                    "where EAMOrgNumber = '"
//                                            + rows
//                                            .getString( "cfcompanyorgunit" )
//                                            + "'" );
//                    EamOrgInfo eamOrgInfo = eamOrgCollection.get( 0 );
//                    // 数据库中读取出来的财务组织非空判断
//                    // 只取第一个，且应该是只有一个
//                    if ( eamOrgCollection.get( 0 ) != null
//                            && !"".equals( eamOrgCollection.get( 0 ) ) ) {
//                        // 根据getEASOrgNumber查询CompanyOrgUnitCollection
//                        EntityViewInfo companyOrgUnitEntityView = new EntityViewInfo( );
//                        FilterInfo companyOrgUnitFilter = new FilterInfo( );
//                        companyOrgUnitFilter.getFilterItems( )
//                                .add(
//                                        new FilterItemInfo( "number", eamOrgInfo
//                                                .getEASOrgNumber( ),
//                                                CompareType.EQUALS ) );
//                        companyOrgUnitEntityView
//                                .setFilter( companyOrgUnitFilter );
//                        CompanyOrgUnitCollection companyOrgUnitCollection = CompanyOrgUnitFactory
//                                .getLocalInstance( ctx )
//                                .getCompanyOrgUnitCollection(
//                                        companyOrgUnitEntityView );
//
//                        if ( companyOrgUnitCollection.get( 0 ) != null ) {
//                            purRequestTSynInfo
//                                    .setCompanyOrgUnit( companyOrgUnitCollection
//                                            .get( 0 ) );
//                        } else {
//                            // 反写错误信息
//                            // failReason +=
//                            // "——找不到number为"+rows.getString("cfcompanyorgunit"
//                            // )+"财务组织，请EAM方确认。";
//                            sb.append( "——找不到number为"
//                                    + rows.getString( "cfcompanyorgunit" )
//                                    + "财务组织，请EAM方确认。" );
//                            sub = false;
//                            i++;
//
//                        }
//                    } else {// 反写错误信息
//                        // failReason +=
//                        // "——找不到对应number为"+rows.getString("cfcompanyorgunit"
//                        // )+"的财务组织，请EAM方确认。";
//                        sb.append( "——找不到对应number为"
//                                + rows.getString( "cfcompanyorgunit" )
//                                + "的财务组织，请EAM方确认。" );
//                        sub = false;
//                        i++;
//                    }
//                } else {
//                    sb.append( "——财务组织编码不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//
//                // 业务类型
//                EntityViewInfo bizTypeEntityView = new EntityViewInfo( );
//                FilterInfo bizTypeFilter = new FilterInfo( );
//                bizTypeFilter.getFilterItems( )
//                        .add(
//                                new FilterItemInfo( "number", "110",
//                                        CompareType.EQUALS ) );
//                bizTypeEntityView.setFilter( bizTypeFilter );
//                BizTypeCollection bizTypeCollection = BizTypeFactory
//                        .getLocalInstance( ctx ).getBizTypeCollection(
//                                bizTypeEntityView );
//                if ( bizTypeCollection.get( 0 ) != null ) {
//                    purRequestTSynInfo.setBizType( bizTypeCollection.get( 0 ) );
//                } else {
//                    // 反写错误信息
//                    // failReason +=
//                    //"——找不到number为"+rows.getString("cfbiztype")+"业务类型，请EAM方确认。"
//                    // ;
//                    sb.append( "——找不到number为" + rows.getString( "cfbiztype" )
//                            + "业务类型，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//                // 需求类型
//                EntityViewInfo demandTypeEntityView = new EntityViewInfo( );
//                FilterInfo demandTypeFilter = new FilterInfo( );
//                demandTypeFilter.getFilterItems( )
//                        .add( new FilterItemInfo( "number", "010",
//                                CompareType.EQUALS ) );
//                demandTypeEntityView.setFilter( demandTypeFilter );
//                DemandTypeCollection demandTypeCollection = DemandTypeFactory
//                        .getLocalInstance( ctx ).getDemandTypeCollection(
//                                demandTypeEntityView );
//                if ( demandTypeCollection.get( 0 ) != null ) {
//                    purRequestTSynInfo.setDemandType( demandTypeCollection
//                            .get( 0 ) );
//                } else {
//                    // 反写错误信息
//                    // failReason +=
//                    // "——找不到number为"+rows.getString("cfdemandtype"
//                    // )+"需求类型，请EAM方确认。";
//                    sb.append( "——找不到number为" + rows.getString( "cfdemandtype" )
//                            + "需求类型，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//                // eam申请人
//                if ( rows.getString( "cfeamperson" ) != null
//                        && !"".equals( rows.getString( "cfeamperson" ) ) ) {
//                    purRequestTSynInfo.setEAMPerson( rows
//                            .getString( "cfeamperson" ) );
//                } else {
//                    // 反写错误信息
//                    // failReason += "——EAM申请人不能为空，请EAM方确认。";
//                    sb.append( "——EAM申请人不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//                // 采购类型
//                EntityViewInfo inviteTypeEntityView = new EntityViewInfo( );
//                FilterInfo inviteTypeFilter = new FilterInfo( );
//                inviteTypeFilter.getFilterItems( ).add(
//                        new FilterItemInfo( "longnumber", "01!01!01",
//                                CompareType.EQUALS ) );
//                inviteTypeEntityView.setFilter( inviteTypeFilter );
//                InviteTypeCollection inviteTypeCollection = InviteTypeFactory
//                        .getLocalInstance( ctx ).getInviteTypeCollection(
//                                inviteTypeEntityView );
//
//                if ( inviteTypeCollection.get( 0 ) != null ) {
//                    purRequestTSynInfo.setInviteType( inviteTypeCollection
//                            .get( 0 ) );
//                } else {
//                    // 反写错误信息
//                    // failReason +=
//                    // "——找不到number为"+rows.getString("cfinvitetype"
//                    // )+"采购类型，请EAM方确认。";
//                    sb.append( "——找不到number为" + rows.getString( "cfinvitetype" )
//                            + "采购类型，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//                // 库存组织
//                if ( rows.getString( "cfsourcestorageorgunit" ) != null
//                        && !"".equals( rows.getString( "cfsourcestorageorgunit" ) ) ) {
//                    EamOrgCollection stCollection = EamOrgFactory
//                            .getLocalInstance( ctx )
//                            .getEamOrgCollection(
//                                    "where EAMOrgNumber = '"
//                                            + rows
//                                            .getString( "cfsourcestorageorgunit" )
//                                            + "'" );
//                    EamOrgInfo stEamOrgInfo = stCollection.get( 0 );
//                    if ( stCollection.get( 0 ) != null
//                            && !"".equals( stCollection.get( 0 ) ) ) {
//                        EntityViewInfo sourceStorageOrgUnitEntityView = new EntityViewInfo( );
//                        FilterInfo sourceStorageOrgUnitFilter = new FilterInfo( );
//                        sourceStorageOrgUnitFilter.getFilterItems( )
//                                .add(
//                                        new FilterItemInfo( "number",
//                                                stEamOrgInfo.getEASOrgNumber( ),
//                                                CompareType.EQUALS ) );
//                        sourceStorageOrgUnitEntityView
//                                .setFilter( sourceStorageOrgUnitFilter );
//                        StorageOrgUnitCollection storageOrgUnitCollection = StorageOrgUnitFactory
//                                .getLocalInstance( ctx )
//                                .getStorageOrgUnitCollection(
//                                        sourceStorageOrgUnitEntityView );
//                        if ( storageOrgUnitCollection.get( 0 ) != null ) {
//                            purRequestTSynInfo
//                                    .setSourceStorageOrgUnit( storageOrgUnitCollection
//                                            .get( 0 ) );
//                        } else {
//                            // 反写错误信息
//                            // failReason += "——找不到number为"+rows.getString(
//                            // "cfsourcestorageorgunit")+"库存组织，请EAM方确认。";
//                            sb.append( "——找不到number为"
//                                    + rows.getString( "cfsourcestorageorgunit" )
//                                    + "库存组织，请EAM方确认。" );
//                            sub = false;
//                            i++;
//
//                        }
//                    } else {// 反写错误信息
//                        // failReason += "——找不到对应number为"+rows.getString(
//                        // "cfsourcestorageorgunit")+"的库存组织，请EAM方确认。";
//                        sb.append( "——找不到对应number为"
//                                + rows.getString( "cfsourcestorageorgunit" )
//                                + "的库存组织，请EAM方确认。" );
//                        sub = false;
//                        i++;
//                    }
//                } else {
//                    sb.append( "——库存组织编码不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//                // 行政组织
//                if ( rows.getString( "cfadminorgunit" ) != null
//                        && !"".equals( rows.getString( "cfadminorgunit" ) ) ) {
//                    EamOrgCollection adminCollection = EamOrgFactory
//                            .getLocalInstance( ctx ).getEamOrgCollection(
//                                    "where EAMOrgNumber = '"
//                                            + rows.getString( "cfadminorgunit" )
//                                            + "'" );
//                    EamOrgInfo adminEamOrgInfo = adminCollection.get( 0 );
//                    if ( adminCollection.get( 0 ) != null
//                            && !"".equals( adminCollection.get( 0 ) ) ) {
//                        EntityViewInfo adminOrgUnitEntityView = new EntityViewInfo( );
//                        FilterInfo adminOrgUnitFilter = new FilterInfo( );
//                        adminOrgUnitFilter.getFilterItems( )
//                                .add(
//                                        new FilterItemInfo( "number",
//                                                adminEamOrgInfo
//                                                        .getEASOrgNumber( ),
//                                                CompareType.EQUALS ) );
//                        adminOrgUnitEntityView.setFilter( adminOrgUnitFilter );
//                        AdminOrgUnitCollection adminOrgUnitCollection = AdminOrgUnitFactory
//                                .getLocalInstance( ctx )
//                                .getAdminOrgUnitCollection(
//                                        adminOrgUnitEntityView );
//                        if ( adminOrgUnitCollection.get( 0 ) != null ) {
//                            purRequestTSynInfo
//                                    .setAdminOrgUnit( adminOrgUnitCollection
//                                            .get( 0 ) );
//                        } else {
//                            // 反写错误信息
//                            // failReason +=
//                            // "——找不到number为"+rows.getString("cfadminorgunit"
//                            // )+"行政组织，请EAM方确认。";
//
//                            sb.append( "——找不到number为"
//                                    + rows.getString( "cfadminorgunit" )
//                                    + "行政组织，请EAM方确认。" );
//                            sub = false;
//                            i++;
//
//                        }
//                    } else {// 反写错误信息
//                        // failReason +=
//                        // "——找不到对应number为"+rows.getString("cfadminorgunit"
//                        // )+"的行政组织，请EAM方确认。";
//                        sb.append( "——找不到对应number为"
//                                + rows.getString( "cfadminorgunit" )
//                                + "的行政组织，请EAM方确认。" );
//                        sub = false;
//                        i++;
//                    }
//                } else {
//                    sb.append( "——财务组织编码不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//
//                // 物料编码
//                MeasureUnitInfo measureUnitInfo = null;
//                if ( rows.getString( "cfmaterial" ) != null
//                        && !"".equals( rows.getString( "cfmaterial" ) ) ) {
//                    EntityViewInfo materialEntityView = new EntityViewInfo( );
//                    FilterInfo materialfilter = new FilterInfo( );
//                    materialfilter.getFilterItems( ).add(
//                            new FilterItemInfo( "number", rows
//                                    .getString( "cfmaterial" ),
//                                    CompareType.EQUALS ) );
//                    materialEntityView.setFilter( materialfilter );
//                    MaterialCollection materialCollection = MaterialFactory
//                            .getLocalInstance( ctx ).getMaterialCollection(
//                                    materialEntityView );
//
//                    if ( materialCollection.get( 0 ) != null ) {
//                        // 获取物料对应计量单位
//                        BOSUuid id = materialCollection.get( 0 ).getBaseUnit( )
//                                .getId( );
//                        MeasureUnitCollection measureUnitCollection2 = MeasureUnitFactory
//                                .getLocalInstance( ctx )
//                                .getMeasureUnitCollection(
//                                        "where id = '" + id + "'" );
//                        measureUnitInfo = measureUnitCollection2.get( 0 );
//
//                        purRequestTSynInfo.setMaterial( materialCollection
//                                .get( 0 ) );
//                    } else {
//                        // 反写错误信息
//                        // failReason +=
//                        // "——找不到number为"+rows.getString("cfmaterial"
//                        // )+"的物料，请EAM方确认。";
//                        sb.append( "——找不到number为" + rows.getString( "cfmaterial" )
//                                + "的物料，请EAM方确认。" );
//                        sub = false;
//                        i++;
//
//                    }
//                } else {
//                    sb.append( "——物料编码不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//
//                // 计量
//                if ( rows.getString( "cfunit" ) != null
//                        && !"".equals( rows.getString( "cfunit" ) ) ) {
//                    EntityViewInfo measureUnitEntityView = new EntityViewInfo( );
//                    FilterInfo measureUnitfilter = new FilterInfo( );
//                    measureUnitfilter.getFilterItems( ).add(
//                            new FilterItemInfo( "name",
//                                    rows.getString( "cfunit" ),
//                                    CompareType.EQUALS ) );
//                    measureUnitEntityView.setFilter( measureUnitfilter );
//                    MeasureUnitCollection measureUnitCollection = MeasureUnitFactory
//                            .getLocalInstance( ctx ).getMeasureUnitCollection(
//                                    measureUnitEntityView );
//                    if ( measureUnitCollection.get( 0 ) != null
//                            && measureUnitCollection.get( 0 ).equals(
//                            measureUnitInfo ) ) {
//                        purRequestTSynInfo
//                                .setUnit( measureUnitCollection.get( 0 ) );
//                    } else {
//                        // 反写错误信息
//                        // failReason +=
//                        //"——找不到name为"+rows.getString("cfunit")+"的计量单位，请EAM方确认。"
//                        // ;
//                        sb.append( "——找不到该物料对应name为" + rows.getString( "cfunit" )
//                                + "的计量单位，请EAM方确认。" );
//                        sub = false;
//                        i++;
//
//                    }
//                } else {
//                    sb.append( "——计量单位不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//
//                if ( rows.getString( "cfrequestqty" ) != null
//                        && isNumeric( ctx, rows.getString( "cfrequestqty" ) )
//                        && Double.parseDouble( rows.getString( "cfrequestqty" ) ) > 0 ) {
//                    // int qty =
//                    // Integer.parseInt(rows.getString("cfrequestqty"));
//                    // if(qty>0){
//                    purRequestTSynInfo.setRequestQty( new BigDecimal( rows
//                            .getString( "cfrequestqty" ) ) );
//
//                    // }
//
//                } else {
//                    // 反写错误信息
//                    // failReason += "——申请数量不能为空或填写格式不正确，请EAM方确认。";
//                    sb.append( "——申请数量不能为空不能≤0，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//                String req = rows.getString( "cfrequirementdate" );
//                if ( req != null && !"".equals( req ) && isDate( ctx, req ) ) {
//                    SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd" );
//
//                    purRequestTSynInfo.setRequirementDate( sdf1.parse( req ) );
//
//                } else {
//                    // 反写错误信息
//                    // failReason += "——需求日期不能为空或日期格式不正确，请EAM方确认。";
//
//                    sb.append( "——需求日期不能为空或日期格式不正确，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//
//                // 采购组织
//                if ( rows.getString( "cfpurchaseorgunit" ) != null
//                        && !"".equals( rows.getString( "cfpurchaseorgunit" ) ) ) {
//                    EamOrgCollection pureamOrgCollection = EamOrgFactory
//                            .getLocalInstance( ctx )
//                            .getEamOrgCollection(
//                                    "where EAMOrgNumber = '"
//                                            + rows
//                                            .getString( "cfpurchaseorgunit" )
//                                            + "'" );
//                    EamOrgInfo purEamOrgInfo = pureamOrgCollection.get( 0 );
//                    if ( pureamOrgCollection.get( 0 ) != null
//                            && !"".equals( pureamOrgCollection.get( 0 ) ) ) {
//                        EntityViewInfo purOrgEntityView = new EntityViewInfo( );
//                        FilterInfo purOrgfilter = new FilterInfo( );
//                        purOrgfilter
//                                .getFilterItems( )
//                                .add(
//                                        new FilterItemInfo(
//                                                "number",
//                                                purEamOrgInfo.getEASOrgNumber( ),
//                                                CompareType.EQUALS ) );
//                        purOrgEntityView.setFilter( purOrgfilter );
//                        PurchaseOrgUnitCollection purchaseOrgUnitCollection = PurchaseOrgUnitFactory
//                                .getLocalInstance( ctx )
//                                .getPurchaseOrgUnitCollection( purOrgEntityView );
//
//                        if ( purchaseOrgUnitCollection.get( 0 ) != null ) {
//                            purRequestTSynInfo
//                                    .setPurchaseOrgUnit( purchaseOrgUnitCollection
//                                            .get( 0 ) );
//                        } else {
//                            // 反写错误信息
//                            // failReason +=
//                            // "——找不到number为"+rows.getString("cfpurchaseorgunit"
//                            // )+"的采购组织，请EAM方确认。";
//                            sb.append( "——找不到number为"
//                                    + rows.getString( "cfpurchaseorgunit" )
//                                    + "的采购组织，请EAM方确认。" );
//                            sub = false;
//                            i++;
//
//                        }
//                    } else {
//                        // failReason +=
//                        // "——找不到对应number为"+rows.getString("cfpurchaseorgunit"
//                        // )+"的采购组织，请EAM方确认。";
//                        sb.append( "——找不到对应number为"
//                                + rows.getString( "cfpurchaseorgunit" )
//                                + "的采购组织，请EAM方确认。" );
//                        sub = false;
//                        i++;
//                    }
//                } else {
//                    sb.append( "——采购组织编码不能为空，请EAM方确认。" );
//                    sub = false;
//                    i++;
//                }
//
//                // 币别
//                EntityViewInfo currencyEntityView = new EntityViewInfo( );
//                FilterInfo currencyfilter = new FilterInfo( );
//                currencyfilter.getFilterItems( )
//                        .add(
//                                new FilterItemInfo( "number", "BB01",
//                                        CompareType.EQUALS ) );
//                currencyEntityView.setFilter( currencyfilter );
//                CurrencyCollection currencyCollection = CurrencyFactory
//                        .getLocalInstance( ctx ).getCurrencyCollection(
//                                currencyEntityView );
//
//                if ( currencyCollection.get( 0 ) != null ) {
//                    purRequestTSynInfo.setCurrency( currencyCollection.get( 0 ) );
//                } else {
//                    // 反写错误信息
//                    // failReason +=
//                    // "——找不到number为"+rows.getString("cfcurrency")+
//                    // "的币别，请EAM方确认。";
//                    sb.append( "——找不到number为" + rows.getString( "cfcurrency" )
//                            + "的币别，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//
//                if ( !"".equals( rows.getString( "cfprice" ) )
//                        && rows.getString( "cfprice" ) != null
//                        && isNumeric( ctx, rows.getString( "cfprice" ) ) ) {
//                    purRequestTSynInfo.setPrice( rows.getBigDecimal( "cfprice" ) );
//                    BigDecimal result = new BigDecimal( rows
//                            .getString( "cfprice" ) ).multiply( new BigDecimal( rows
//                            .getString( "cfrequestqty" ) ) );
//                    if ( !"".equals( rows.getString( "cfamount" ) )
//                            && rows.getString( "cfamount" ) != null
//                            && new BigDecimal( rows.getString( "cfamount" ) )
//                            .compareTo( result ) == 0 ) {
//                        purRequestTSynInfo.setAmount( rows
//                                .getBigDecimal( "cfamount" ) );
//                    } else {
//                        // 反写错误信息
//                        // failReason += "——金额不能为空或格式不正确，请EAM方确认。";
//                        sb.append( "——金额不能为空或格式不正确，金额=申请数量*单价。请EAM方确认。" );
//                        sub = false;
//                        i++;
//
//                    }
//                } else {
//                    // 反写错误信息
//                    // failReason += "——价格不能为空或格式不正确 ，请EAM方确认。";
//                    sb.append( "——价格不能为空或格式不正确 ，请EAM方确认。" );
//                    sub = false;
//                    i++;
//
//                }
//                // if(rows.getString("cfexchangerate") != null && isNumeric(ctx,
//                // rows.getString("cfexchangerate"))){rows.getBigDecimal(
//                // "cfexchangerate")
//                purRequestTSynInfo.setExchangeRate( new BigDecimal( "1.00" ) );
//                // }else{
//                // // 反写错误信息
//                // failReason += "——汇率不能为空或格式不正确，请EAM方确认。";
//                // sub = false;
//                // i++;
//                // returnFailReason(ctx, failReason,fid );
//                // break;
//                // }
//
//                purRequestTSynInfo.setPurpose( rows.getString( "cfpurpose" ) );
//                purRequestTSynInfo.setNote( rows.getString( "cfnote" ) );
//
//                if ( sub ) {
//
//                    IObjectPK pk = PurRequestTSynFactory.getLocalInstance( ctx )
//                            .save( purRequestTSynInfo );
//
//                    System.out.println( "同步完毕ID为'" + pk.toString( ) + "'" );
//                    String sql1 = "/*dialect*/ update CT_EAM_PurRequestTotalSyn set CFISSYN = 'Y',cfstate = 'Y',
//                    CFFailReason = null where fid ='"
//                            + rows.getString( "fid" ) + "'";
//                    DbUtil.execute( ctx, sql1 );
//                } else {
//
//                    System.out.println( "采购申请单同步结果：中间表ID为'" + fid + "'的数据共>'"
//                            + i + "'<个错误,错误为：'" + sb.toString( ) + "'" );
//                    returnFailReason( ctx, sb.toString( ), fid );
//                }
//
//            }
//
//        } catch ( SQLException e ) {
//            // TODO Auto-generated catch block
//            e.printStackTrace( );
//            System.out.println( e.getMessage( ) );
//        } catch ( ParseException e ) {
//            // TODO Auto-generated catch block
//            e.printStackTrace( );
//            System.out.println( e.getMessage( ) );
//        }
//
//    }
//
//    @Override
//    public void returnFailReason( Context ctx, String msg, String voucherNum )
//            throws BOSException, EASBizException {
//        String sql = "/*dialect*/ update CT_EAM_PurRequestTotalSyn set CFISSYN = 'Y',cfstate = 'N',CFFailReason = '"
//                + msg + "' where fid = '" + voucherNum + "'";
//        DbUtil.execute( ctx, sql );
//        System.out.println( msg );
//    }
//
//    @Override
//    public boolean isNumeric( Context ctx, String str ) throws BOSException,
//            EASBizException {
//
//        Pattern pattern = Pattern.compile( "^(\\+)?\\d+(\\.\\d+)?$" );
//        Matcher isNum = pattern.matcher( str );
//        if ( !isNum.matches( ) ) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean isDate( Context ctx, String str ) throws BOSException,
//            EASBizException {
//
//        String rexp1 = "((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|
//        ([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|
//        ([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])
//        |([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|
//        (2[0-8]))))))";
//
//        Pattern pattern1 = Pattern.compile( rexp1 );
//        Matcher isNum1 = pattern1.matcher( str );
//        if ( !isNum1.matches( ) ) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 领料出库导入
//     */
//    @Override
//    protected void _materialReq( Context ctx ) throws BOSException,
//            EASBizException {
//        System.out.println( "领料出库导入" );
//
//        // TODO Auto-generated method stub
//        String sql = "select * from T_EAM_OUTSTOCK";
//        IRowSet rows = DbUtil.executeQuery( ctx, sql );
//        try {
//            while ( rows.next( ) ) {
//                MaterialReqBillInfo materialReqBillInfo = new MaterialReqBillInfo( );
//
//                MaterialReqBillFactory.getLocalInstance( ctx ).save(
//                        materialReqBillInfo );
//
//            }
//        } catch ( SQLException e ) {
//            // TODO Auto-generated catch block
//            e.printStackTrace( );
//        }
//        super._materialReq( ctx );
//    }
//
//    /**
//     * 采购入库导入
//     */
//    @Override
//    protected void _purInput( Context ctx ) throws BOSException, EASBizException {
//        System.out.println( "采购入库导入" );
//
//        // TODO Auto-generated method stub
//        String sql = "select * from T_EAM_WAREHOUSING";
//        IRowSet rows = DbUtil.executeQuery( ctx, sql );
//        try {
//            while ( rows.next( ) ) {
//                PurInWarehsBillInfo purInWarehsBillInfo = new PurInWarehsBillInfo( );
//
//                PurInWarehsBillFactory.getLocalInstance( ctx ).save(
//                        purInWarehsBillInfo );
//
//            }
//        } catch ( SQLException e ) {
//            // TODO Auto-generated catch block
//            e.printStackTrace( );
//        }
//        super._purInput( ctx );
//    }
//}