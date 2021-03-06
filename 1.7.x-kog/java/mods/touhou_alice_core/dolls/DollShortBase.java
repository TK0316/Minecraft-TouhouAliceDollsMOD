////////////////////////////////////////////////////////////////////////////////
// アリスの人形MOD

package mods.touhou_alice_core.dolls;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.touhou_alice_core.client.*;
import mods.touhou_alice_core.AI.*;
import mods.touhou_alice_core.TouhouAliceCore;
import mods.touhou_alice_core.EntityAliceDoll;

/**
 * 人形のベースクラス(Short)
 */
public class DollShortBase extends DollBase
{
    public DollShortBase()
    {
        // アイテムの表示名を設定
//        LanguageRegistry.instance().addStringLocalization(
//            "item.alicedoll.bareshort.name", "en_US", "Bare Doll Mk-II");
//        LanguageRegistry.instance().addStringLocalization(
//            "item.alicedoll.bareshort.name", "ja_JP", "素体人形 Mk-II");
    }
    
    /** 人形の名前 */
    public String getDollName()
    {
        return "bareshort";
    }

    /** 人形アイテムのアイコン名 */
    public String getIconName()
    {
        return this.getDollName();
    }

    /**
     * メインテクスチャのパス
     */
    public String getMainTexturePath()
    {
        return "textures/dolls/bareshort.png";
    }

    /**
     * 防具テクスチャのパス
     * @param type 防具の素材
     * @param slot 防具の種類
     * @param var テクスチャのバリエーション
     */
    public String getArmorTexturePath(int type, int slot ,String var)
    {
        return String.format("textures/dolls/armor/short/%d_%d%s.png",type, (slot<=1?1:2),
                             (var == null ? "" : var));
    }

    /**
     * 人形のレシピを追加する
     */
    public void addRecipes()
    {
         GameRegistry.addRecipe(
             new ItemStack(TouhouAliceCore.instance.itemAliceDoll, 1,
                           DollRegistry.getDollID(getDollName())),
             " W ",
             "WHW",
             " W ",
             'W', Blocks.wool,
             'H', new ItemStack(TouhouAliceCore.instance.itemAliceDoll, 1,
                     DollRegistry.getDollID("bare")));
    }

    /**
     * 人形の高さを取得する
     */
    public float getHeight()
    {
        return 1.2F;
    }

    /**
     * 人形の幅を取得する
     */
    public float getWidth()
    {
        return 0.5F;
    }

    /**
     * 人形の体力を取得する
     */
    public double getHealth()
    {
        return 12.0D;
    }
    
    /**
     * 人形の移動速度を取得する
     */
    public double getSpeed()
    {
        return 0.28D;
    }
    
    /**
     * 人形がふわふわ落下するかどうかを取得する
     */
    public boolean isSlowFall()
    {
        return true;
    }
    
    /**
     * 手持ちアイテムを取得する
     */
    public ItemStack getHeldItem()
    {
        return null;
    }
    
    /**
     * 隠しかどうかを取得する
     */
    public boolean isSecret()
    {
    	return true;
    }

    /**
     * AIの初期化が必要なときに呼ばれる
     */
    public void onInitializeAI(EntityAliceDoll doll)
    {
        super.onInitializeAI(doll);
    }

    @SideOnly(Side.CLIENT)
    /**
     * 人形のModelを生成する
     */
    public ModelBiped getModelInstance(float expand)
    {
        ModelAliceDoll model = new ModelAliceDoll(expand, 0.0f, 64, 64);

        model.setRenderType(getRenderType());

        genBaseModel(model, expand);
        genAccessory(model, expand);

        return model;
    }

    @SideOnly(Side.CLIENT)
    /**
     * 人形のベースモデルを生成する
     */
    protected void genBaseModel(ModelAliceDoll model, float expand)
    {
        model.addBox(0, 0, false, -3F, -6F, -3F, 6, 6, 6, 0F,
                     0F, 10F, 0F, 0F, 0F, 0F, "head", null);
        model.addBox(24, 0, false, -3F, -6F, -3F, 6, 6, 6, 0.1F,
                     0F, 10F, 0F, 0F, 0F, 0F, "headwear", null);
        model.addBox(0, 12, false, -3F, 0F, -1.5F, 6, 7, 3, 0F,
                     0F, 10F, 0F, 0F, 0F, 0F, "body", null);
        model.addBox(0, 22, false, -2F, -1F, -1F, 2, 6, 2, 0F,
                     -3F, 11F, 0F, 0F, 0F, 0F, "rightarm", null);
        model.addBox(8, 22, false, 0F, -1F, -1F, 2, 6, 2, 0F,
                     3F, 11F, 0F, 0F, 0F, 0F, "leftarm", null);
        model.addBox(0, 30, false, -1F, 0F, -1.5F, 3, 7, 3, 0F,
                     -2F, 17F, 0F, 0F, 0F, 0F, "rightleg", null);
        model.addBox(12, 30, false, -2F, 0F, -1.5F, 3, 7, 3, 0F,
                     2F, 17F, 0F, 0F, 0F, 0F, "leftleg", null);
    }

    @SideOnly(Side.CLIENT)
    /**
     * 人形の装飾を生成する
     */
    protected void genAccessory(ModelAliceDoll model, float expand)
    {
        model.addBox(24, 12, false, -4F, 1F, -2F, 8, 3, 4, 0F,
                     0F, 15F, 0F, 0F, 0F, 0F, "skirt1", "body");
    }

    @SideOnly(Side.CLIENT)
    /**
     * 人形のレンダータイプを取得する
     */
    public EnumDollRenderType getRenderType()
    {
        return EnumDollRenderType.SHORT;
    }
}
