<template>
  <q-editor ref="editor" v-model="editorContent" :toolbar="toolbar" style="width: 100%"
            @update:model-value="contentChange">
    <template v-slot:color>
      <q-btn-dropdown ref="color" :class="darkTheme" dense icon="border_color" no-caps no-wrap size="sm"
                      unelevated>
        <q-list dense>
          <q-item clickable style="padding-right: 0;" tag="label">
            <q-item-section side>
              <q-icon name="format_color_fill"/>
            </q-item-section>
            <q-item-section>
              <q-color v-model="backColor" :palette="backColorPalette" default-view="palette" no-footer
                       no-header @click="changeColor('backColor', backColor)"/>
            </q-item-section>
          </q-item>
          <q-item clickable style="padding-right: 0;" tag="label">
            <q-item-section side>
              <q-icon name="format_color_text"/>
            </q-item-section>
            <q-item-section>
              <q-color v-model="foreColor" :palette="foreColorPalette" default-view="palette" no-footer
                       no-header @click="changeColor('foreColor', foreColor)"/>
            </q-item-section>
          </q-item>
        </q-list>
      </q-btn-dropdown>
    </template>
  </q-editor>
</template>

<script setup>
import useTheme from 'src/composables/useTheme';
import {ref, watch} from 'vue';
import {useQuasar} from 'quasar';

const $q = useQuasar();
const {darkTheme} = useTheme()

const foreColor = ref('#000000');
const backColor = ref('#ffffff');
const editor = ref(null);
const color = ref(null);
const backColorPalette = ref([
  '#ffffff', '#000000', '#ffccccaa', '#ffe6ccaa',
  '#ffffccaa', '#ccffccaa', '#ccffe6aa', '#ccffffaa',
  '#cce6ffaa', '#ccccffaa', '#ff0000', '#ff8000',
  '#ffff00', '#00ff00aa', '#00ff80aa', '#00ffff',
  '#0080ff', '#0000ff', '#8000ff', '#ff00ff',

])
const foreColorPalette = ref([
  '#ffffff', '#000000', '#ffccccaa', '#ffe6ccaa',
  '#ffffccaa', '#ccffccaa', '#ccffe6aa', '#ccffffaa',
  '#cce6ffaa', '#ccccffaa', '#ff0000', '#ff8000',
  '#ffff00', '#00ff00aa', '#00ff80aa', '#00ffff',
  '#0080ff', '#0000ff', '#8000ff', '#ff00ff',
])

const changeColor = (cmd, name) => {
  const edit = editor.value
  color.value.hide()
  edit.caret.restore()
  edit.runCmd(cmd, name)
  edit.focus()
}

const props = defineProps({
  modelValue: {
    type: String,
    required: false,
    default: "",
  },
})
const editorContent = ref(props.modelValue)
watch(props, () => {
  editorContent.value = props.modelValue
})

const emit = defineEmits(['update:modelValue'])
const contentChange = (vlaue) => {
  emit('update:modelValue', vlaue)
}
const toolbar = [
  ['unordered', 'ordered'],
  ['outdent', 'indent'],
  [
    {
      label: $q.lang.editor.align,
      icon: $q.iconSet.editor.align,
      fixedLabel: true,
      options: ['left', 'center', 'right', 'justify'],
    },
  ],
  ['color', 'bold', 'italic', 'strike', 'underline', 'subscript', 'superscript', 'hr'],
  [
    {
      label: $q.lang.editor.formatting,
      icon: $q.iconSet.editor.formatting,
      list: 'icons',
      options: ['h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'p', 'code'],
    },
  ],
  [
    {
      label: $q.lang.editor.fontSize,
      icon: $q.iconSet.editor.fontSize,
      list: 'no-icons',
      options: ['size-1', 'size-2', 'size-3', 'size-4', 'size-5', 'size-6', 'size-7'],
    },
  ],

  ['link', 'quote', 'removeFormat'],
  ['undo', 'redo', 'print', 'fullscreen', 'viewsource'],
]
</script>
