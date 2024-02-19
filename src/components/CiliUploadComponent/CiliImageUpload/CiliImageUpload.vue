<template>
  <div class="card">
    <FileUpload :maxFileSize="1000000" :url="uploadUrl" accept="image/*" fileLimit=1 name="file"
                @select="files" @upload="onTemplatedUpload($event)">
      <template #header="{ chooseCallback, uploadCallback, clearCallback, files }">
        <div class="flex flex-wrap justify-content-between align-items-center flex-1 gap-2">
          <div class="flex gap-2">
            <Button icon="pi pi-images" outlined rounded @click="chooseCallback()"></Button>
            <Button :disabled="!files || files.length === 0" icon="pi pi-cloud-upload" outlined rounded
                    severity="success"
                    @click="uploadEvent(uploadCallback)"></Button>
            <Button :disabled="!files || files.length === 0" icon="pi pi-times" outlined rounded severity="danger"
                    @click="clearCallback()"></Button>
          </div>
          <ProgressBar
            :class="['md:w-20rem h-1rem w-full md:ml-auto', { 'exceeded-progress-bar': totalSizePercent > 100 }]"
            :showValue="false"
            :value="totalSizePercent"
          ><span class="white-space-nowrap">{{ totalSize }}B / 1Mb</span></ProgressBar
          >
        </div>
      </template>
      <template #content="{ files, uploadedFiles, removeUploadedFileCallback, removeFileCallback }">
        <div v-if="files.length > 0">
          <h5 class="no-padding no-margin">Pending</h5>
          <div class="flex flex-wrap p-0 sm:p-5 gap-5">
            <div v-for="(file, index) of files" :key="file.name + file.type + file.size"
                 class="card m-0 px-6 flex flex-column border-1 surface-border align-items-center gap-3">
              <div>
                <img :alt="file.name" :src="file.objectURL" height="50" role="presentation" width="100"/>
              </div>
              <span class="font-semibold">{{ file.name }}</span>
              <div>{{ formatSize(file.size) }}</div>
              <Badge severity="warning" value="Pending"/>
              <Button icon="pi pi-times" outlined rounded
                      severity="danger" @click="onRemoveTemplatingFile(file, removeFileCallback, index)"/>
            </div>
          </div>
        </div>
        <div v-if="uploadedFiles.length > 0">
          <h5>Completed</h5>
          <div class="flex flex-wrap p-0 sm:p-5 gap-5">
            <div v-for="(file, index) of uploadedFiles" :key="file.name + file.type + file.size"
                 class="card m-0 px-6 flex flex-column border-1 surface-border align-items-center gap-3">
              <div>
                <img :alt="file.name" :src="file.objectURL" height="50" role="presentation" width="100"/>
              </div>
              <span class="font-semibold">{{ file.name }}</span>
              <div>{{ formatSize(file.size) }}</div>
              <Badge class="mt-3" severity="success" value="Completed"/>
              <Button icon="pi pi-times" outlined rounded severity="danger" @click="removeUploadedFileCallback(index)"/>
            </div>
          </div>
        </div>
      </template>
      <template #empty>
        <div class="flex align-items-center justify-content-center flex-column">
          <i class="pi pi-cloud-upload border-2 border-circle p-5 text-8xl text-400 border-400"/>
          <p class="mt-4 mb-0">Drag and drop files to here to upload.</p>
        </div>
      </template>
    </FileUpload>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {usePrimeVue} from 'primevue/config';
import {useToast} from "primevue/usetoast";

const $primevue = usePrimeVue();
const toast = useToast();

const totalSize = ref(0);
const totalSizePercent = ref(0);
const files = ref([]);

const uploadUrl = ref(process.env.API + 'upload/upload-video')
const onRemoveTemplatingFile = (file, removeFileCallback, index) => {
  removeFileCallback(index);
  totalSize.value -= parseInt(formatSize(file.size));
  totalSizePercent.value = totalSize.value / 10;
};
const onClearTemplatingUpload = (clear) => {
  clear();
  totalSize.value = 0;
  totalSizePercent.value = 0;
};

const onSelectedFiles = (event) => {
  files.value = event.files;
  files.value.forEach((file) => {
    totalSize.value += parseInt(formatSize(file.size));
  });
};

const uploadEvent = (callback) => {
  totalSizePercent.value = totalSize.value / 10;
  console.log(callback());
};
const emit = defineEmits(['setPoster'])
const onTemplatedUpload = ({xhr}) => {
  const {readyState, status, response} = xhr
  if (readyState === 4 && status === 200) {
    const resp = JSON.parse(response)
    if (resp.code === 0) {
      toast.add({severity: "error", summary: "Error", detail: resp.message, life: 3000});
    } else {
      emit('setPoster', {files: files.value, poster: resp.data.records})
      toast.add({severity: "success", summary: "Success", detail: resp.message, life: 3000});
    }
  }

};

const formatSize = (bytes) => {
  const k = 1024;
  const dm = 3;
  const sizes = $primevue.config.locale.fileSizeTypes;

  if (bytes === 0) {
    return `0 ${sizes[0]}`;
  }

  const i = Math.floor(Math.log(bytes) / Math.log(k));
  const formattedSize = parseFloat((bytes / Math.pow(k, i)).toFixed(dm));

  return `${formattedSize} ${sizes[i]}`;
};
defineProps({
  files: Object
})
</script>
