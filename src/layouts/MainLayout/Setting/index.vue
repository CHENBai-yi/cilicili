<template>
  <div>
    <q-btn :icon="`${!right ? 'ion-ios-options' : 'ion-md-close'}`" dense flat round @click="right = !right"/>
    <q-dialog v-model="right">
      <q-card class="text-center" style="height: 100%; width: 890px; max-width: 80vw;">
        <q-card-section>
          <div class="text-h6">
            {{ $t('Customize') + $t('Your') }}
            {{ gqaFrontend.subTitle }}
            {{ $t('Display') + $t('Style') }}
          </div>
        </q-card-section>
        <q-card-section>
          <q-tabs v-model="tab" active-color="primary" align="justify" class="text-grey" dense
                  indicator-color="primary" narrow-indicator>
            <q-tab :label="$t('Basic') + $t('Setting')" name="basicSetting"/>
            <q-tab :label="$t('Theme') + $t('Color')" name="themeColor"/>
            <q-tab :label="$t('Theme') + $t('Style')" name="themeStyle"/>
          </q-tabs>

          <q-separator/>

          <q-tab-panels v-model="tab" animated>
            <q-tab-panel class="text-left q-gutter-y-xl" name="basicSetting">
              <div class="row q-gutter-x-sm">
                <q-field :label="$t('Dark') + $t('Theme')" class="col" dense stack-label
                         style="width: 20%;">
                  <template v-slot:control>
                    <DarkTheme/>
                  </template>
                </q-field>
                <q-field :label="$t('Dense') + $t('Sidebar')" class="col" dense stack-label
                         style="width: 20%;">
                  <template v-slot:control>
                    <SidebarDense/>
                  </template>
                </q-field>
              </div>
              <GqaLanguage class="col-2"/>
              <SidebarWidth/>
            </q-tab-panel>

            <q-tab-panel name="themeColor">
              <GqaThemeColor/>
            </q-tab-panel>

            <q-tab-panel name="themeStyle">
              <GqaThemeStyle/>
            </q-tab-panel>
          </q-tab-panels>
        </q-card-section>
      </q-card>
    </q-dialog>
  </div>
</template>

<script setup>
import GqaLanguage from 'src/components/GqaLanguage/index.vue'
import GqaThemeColor from 'src/components/GqaTheme/GqaThemeColor.vue'
import GqaThemeStyle from 'src/components/GqaTheme/GqaThemeStyle.vue'
import DarkTheme from 'src/components/GqaTheme/DarkTheme.vue';
import SidebarDense from './SidebarDense.vue';
import SidebarWidth from './SidebarWidth.vue'
import {computed, ref} from 'vue';
import {useStorageStore} from 'src/stores/storage';

const right = ref(false)
const tab = ref('basicSetting')
const storageStore = useStorageStore();
const gqaFrontend = computed(() => storageStore.GetGqaFrontend())
</script>
