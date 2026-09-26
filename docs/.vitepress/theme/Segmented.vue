<script setup lang="ts">
/**
 * 分段控件：学习路线档位切换、目录导览模块切换
 */
const props = defineProps<{ options: { label: string; value: string }[] }>()
const model = defineModel<string>({ required: true })

function pick(v: string) {
  model.value = v
}
</script>

<template>
  <div class="ds-segmented" role="tablist">
    <button
      v-for="o in props.options"
      :key="o.value"
      type="button"
      role="tab"
      :aria-selected="model === o.value"
      @click="pick(o.value)"
    >
      {{ o.label }}
    </button>
  </div>
</template>
