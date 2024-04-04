<template>
  <div></div>
</template>

<script>
import {defineComponent, h} from "vue";
import {NAlert, useMessage} from "naive-ui";

export default defineComponent({
  setup() {
    const renderMessage = (props) => {
      const {type} = props;
      return h(
        NAlert,
        {
          closable: props.closable,
          onClose: props.onClose,
          type: type === "loading" ? "default" : type,
          style: {
            boxShadow: "var(--n-box-shadow)",
            maxWidth: "calc(100vw - 32px)",
            width: "480px",
          }
        },
        {
          default: () => props.content
        }
      );
    };
    window.$message = useMessage();
    window.$render = renderMessage;
  },
});
</script>
