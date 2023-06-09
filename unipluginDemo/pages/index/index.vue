<template>
	<view class="uni-container">
		<view class="uni-hello-text">
			<text class="hello-text">uni原生插件示例</text>
		</view>
		<view class="uni-panel" v-for="(item, index) in list" :key="item.id">
			<view class="uni-panel-h" :class="item.open ? 'uni-panel-h-on' : ''" @click="triggerCollapse(index)">
				<text class="uni-panel-text">{{item.name}}</text>
			</view>
			<view class="uni-panel-c" v-if="item.open">
				<view class="uni-navigate-item" v-for="(item2,key) in item.pages" :key="key"
					@click="goDetailPage(item2.url)">
					<text class="uni-navigate-text">{{item2.name ? item2.name : item2}}</text>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				list: [{
						id: 'oaid-module',
						name: '获取OAID',
						open: false,
						url: '/pages/sample/oaid-module'
					},
					{
						id: 'ext-module',
						name: '扩展 module',
						open: false,
						url: '/pages/sample/ext-module'
					},
					{
						id: 'ext-component',
						name: '扩展 component',
						open: false,
						url: '/pages/sample/ext-component'
					},
					{
						id: 'richAlert',
						name: '插件示例RichAlert',
						open: false,
						url: '/pages/sample/richAlert'
					}
				],
				navigateFlag: false
			}
		},
		onLoad() {},
		methods: {
			triggerCollapse(e) {
				if (!this.list[e].pages) {
					this.goDetailPage(this.list[e].url);
					return;
				}
				for (var i = 0; i < this.list.length; ++i) {
					if (e === i) {
						this.list[i].open = !this.list[e].open;
					} else {
						this.list[i].open = false;
					}
				}
			},
			goDetailPage(e) {
				if (this.navigateFlag) {
					return;
				}
				this.navigateFlag = true;
				uni.navigateTo({
					url: e
				});
				setTimeout(() => {
					this.navigateFlag = false;
				}, 200)
				return false;
			}
		}
	}
</script>

<style>
</style>