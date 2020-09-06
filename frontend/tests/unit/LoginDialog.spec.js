import { shallowMount, createLocalVue, mount } from "@vue/test-utils";
import dialog from "@/components/LoginDialog.vue"
import ElementUI from "element-ui";
import Vuex from "vuex"

const localVue = createLocalVue();
localVue.use(ElementUI);
localVue.use(Vuex);

jest.mock("axios", () => ({
    post: () => Promise.resolve({
        data: {
            status: 0,
            extraData: {
                username: 'test',
                userType: [],
                birthday: '',
                mailAddr: ''
            }
        },
        status: 200,
    })
}));

describe("init", () => {
    it("init state", () => {
        const wrapper = shallowMount(dialog, {
            localVue,
            // mocks: {
            //     axios
            // },
        });
        /**
         * 检查data的初始状态
         */
        expect(wrapper.vm.loginForm.username).toEqual('');
        expect(wrapper.vm.loginForm.password).toEqual('');
        expect(wrapper.vm.registerForm.username).toEqual('');
        expect(wrapper.vm.registerForm.password).toEqual('');
    });
});

const $router = {
    push: () => "true",
    back: () => true,
};
const $message = {
    success: () => "true"
}

/**
 * 异步和mock
 */
describe("login", () => {
    let store;
    beforeEach(() => {
        store = new Vuex.Store({
            state: {
                logged: false,
                userData: {
                    username: '',
                    userType: [],
                    birthday: '',
                    mailAddr: ''
                }
            },
            mutations: {
                setUserData(state, userData) {
                    state.userData = userData;
                    state.logged = true;
                }
            }
        })
    })
    it("login success by click", async () => {
        /**
         * 如果使用element-ui等第三方组件库，不要使用shallowMount
         */
        const wrapper = mount(dialog, {
            store,
            localVue,
            mocks: {
                $router,
                $message
            },
        });
        /**
         * 通过css选择器找到登录按钮，并检查是否找到
         */
        const btn = wrapper.find('#login-btn');
        expect(btn.exists()).toBeTruthy();
        /**
         * 模拟点击。由于login是async的，因此需要await点击完成
         */
        await btn.trigger('click');
        await wrapper.vm.$nextTick();
        expect(wrapper.vm.$store.state.logged).toBeTruthy();
    });
});

// describe("register", () => {
//
//     it("register success by click", async () => {
//         /**
//          * 如果使用element-ui等第三方组件库，不要使用shallowMount
//          */
//         const wrapper = mount(dialog, {
//             localVue,
//             mocks: {
//                 $store,
//                 $router,
//                 $message
//             },
//         });
//
//         wrapper.vm.activeName = "register";
//         const btn = wrapper.find('#register-button');
//         expect(btn.exists()).toBe(true);
//
//         await btn.trigger('click');
//         wrapper.vm.$nextTick(() => {
//             expect(wrapper.vm.activeName).toEqual("login");
//         });
//     });
// });
