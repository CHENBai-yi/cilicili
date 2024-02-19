<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="课程名称：">
      <el-input v-model="form.name"/>
    </el-form-item>
    <el-form-item label="授课教师：">
      <el-select v-model="form.teacher" placeholder="please select your zone">
        <el-option label="Zone one" value="shanghai"/>
        <el-option label="Zone two" value="beijing"/>
      </el-select>
    </el-form-item>
    <el-form-item label="课程分类：">
      <el-col :span="4">
        <n-select v-model:value="form.catalog" :options="options" clearable filterable multiple/>
      </el-col>
      <el-col :span="2" class="text-center">
        <span class="text-gray-500">-</span>
      </el-col>
      <el-col :span="4">
        <n-select v-model:value="form.catalog" :options="options" clearable filterable multiple/>
      </el-col>
    </el-form-item>
    <el-form-item label="课程价格：">
      <el-radio-group v-model="form.price">
        <el-radio label="免费"/>
        <el-radio>
          <template #default>
            自定义价格
            <el-input-number v-model="form.price" :max="10" :precision="2" :step="0.1"/>
          </template>
        </el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="课程描述：">
      <el-input v-model="form.desc" type="textarea"/>
    </el-form-item>
    <el-form-item label="课程卖点：">
      <q-select
        v-model="form.tag"
        clearable
        hide-dropdown-icon
        input-debounce="0"
        label="例如：该课程包含的知识点或技能"
        multiple
        new-value-mode="add-unique"
        outlined
        style="width: 250px"
        use-chips
        use-input

      />
    </el-form-item>
  </el-form>
</template>

<script setup>
import {reactive} from 'vue'

// do not use same name with ref
const form = reactive({
  name: '',
  teacher: '',
  catalog: [],
  date2: '',
  delivery: false,
  type: [],
  price: '',
  desc: '',
  tag: null
})

const onSubmit = () => {
  console.log('submit!')
}

const options = reactive([
  {
    label: "Everybody's Got Something to Hide Except Me and My Monkey",
    value: 'song0',
    disabled: true,
    type: 'success'
  },
  {
    label: 'Drive My Car',
    value: 'song1',
    type: 'success'
  },
  {
    label: 'Norwegian Wood',
    value: 'song2'
  },
  {
    label: "You Won't See",
    value: 'song3',
    disabled: true
  },
  {
    label: 'Nowhere Man',
    value: 'song4'
  },
  {
    label: 'Think For Yourself',
    value: 'song5'
  },
  {
    label: 'The Word',
    value: 'song6'
  },
  {
    label: 'Michelle',
    value: 'song7',
    disabled: true
  },
  {
    label: 'What goes on',
    value: 'song8'
  },
  {
    label: 'Girl',
    value: 'song9'
  },
  {
    label: "I'm looking through you",
    value: 'song10'
  },
  {
    label: 'In My Life',
    value: 'song11',
    type: 'success'
  },
  {
    label: 'Wait',
    value: 'song12',
    type: 'success'
  }
])
defineExpose({
  form
})
</script>
